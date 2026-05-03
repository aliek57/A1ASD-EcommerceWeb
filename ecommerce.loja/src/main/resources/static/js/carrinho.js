const CART_KEY = "cart_items_v1";
const listaItens = document.getElementById("listaItens");
const valorTotalEl = document.getElementById("valorTotal");
const fmtBRL = v => new Intl.NumberFormat("pt-BR", { style: "currency", currency: "BRL"}).format(v);
const btnBuscar = document.getElementById("btnBuscarCep");

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

carregarCarrinho();
carregarCep();