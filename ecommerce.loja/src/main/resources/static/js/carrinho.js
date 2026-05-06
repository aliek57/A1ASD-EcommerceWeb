const CART_KEY = "cart_items_v1";
const listaItens = document.getElementById("listaItens");
const valorTotalEl = document.getElementById("valorTotal");
const fmtBRL = v => new Intl.NumberFormat("pt-BR", { style: "currency", currency: "BRL"}).format(v);
const btnBuscar = document.getElementById("btnBuscarCep");
const btnComprar = document.getElementById("btnComprar");

function carregarCarrinho() {
    const cart = JSON.parse(localStorage.getItem(CART_KEY)) || {};
    const items = Object.values(cart);
    
    if (items.length === 0) {
        listaItens.innerHTML = "<p>Carrinho vazio.</p>";
        return;
    }

    let total = 0;
    listaItens.innerHTML = items.map(p => {
        const subtotal = p.preco * p.qty;
        total += subtotal;
        return `
            <div class="row" style="justify-content: space-between; padding: 10px 0; border-bottom: 1px solid #eee;">
                <span><strong>${p.nome}</strong> (x${p.qty})</span>
                <span>${fmtBRL(subtotal)}</span>
            </div>
        `;
    }).join("");

    valorTotalEl.textContent = fmtBRL(total);
}

function carregarCep() {
	btnBuscar.addEventListener("click", async () => {
		const cep = document.getElementById("cep").value;
	    if (cep.length < 8) return alert("CEP inválido");
	    
	    try {
	        const res = await fetch(`http://localhost:8080/ecommerce.loja/cep/${cep}`);
	        const dados = await res.json();
	
	        document.getElementById("logradouro").value = dados.logradouro;
	        document.getElementById("bairro").value = dados.bairro;
	        document.getElementById("cidade").value = `${dados.cidade} - ${dados.estado}`;
	    } catch (err) {
	        alert("Erro ao buscar CEP");
	    }
	});
}

function enviarCompra() {
	btnComprar.addEventListener("click", async () => {
    const cart = JSON.parse(localStorage.getItem("cart_items_v1")) || {};
    const itens = Object.values(cart);
    
    if (itens.length === 0) {
        return alert("O carrinho está vazio!");
    }
    
    const nome = document.getElementById("nome").value.trim();
    const email = document.getElementById("email").value.trim();
    const cep = document.getElementById("cep").value.trim();
    const cartao = document.getElementById("cartao").value.trim();
    const logradouro = document.getElementById("logradouro").value;
    
    if (!nome || !email || !cep || !cartao) {
        return alert("Preencha todos os campos para finalizar a compra.");
    }
    
    if (!logradouro) {
        return alert("Valide o CEP antes de finalizar a compra.");
    }
    
    const total = itens.reduce((s, it) => s + (it.preco * it.qty), 0);

    const venda = {
	    nomeCliente: nome,
	    emailCliente: email,
	    cep: cep,
	    numeroCartao: cartao,
	    itens: itens,
	    valorTotal: total
    };

    try {
        const res = await fetch("http://localhost:8080/ecommerce.loja/finalizar", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(venda)
        });
        
        if (!res.ok) throw new Error("Erro na resposta do servidor");
        
        const resultado = await res.json();
        
        if(resultado.statusPagamento === "Aprovado") {
            alert("Compra realizada com sucesso!");
            localStorage.removeItem("cart_items_v1");
            window.location.href = "loja.html";
        }
    } catch (err) {
        console.error(err);
        alert("Erro ao finalizar compra.");
    }
});
}

carregarCarrinho();
carregarCep();
enviarCompra();