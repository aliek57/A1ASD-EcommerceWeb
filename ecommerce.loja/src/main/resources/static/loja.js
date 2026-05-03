	const API = "http://localhost:8080/ecommerce.loja/catalogo"; // ajuste se necessário
    const grid = document.getElementById("grid");
    const statusEl = document.getElementById("status");
    const errorEl = document.getElementById("error");
    const emptyEl = document.getElementById("empty");
    const q = document.getElementById("q");
    const ord = document.getElementById("ord");
    const cartCount = document.getElementById("cartCount");
    const cardTmpl = document.getElementById("cardTmpl");
    document.getElementById("year").textContent = new Date().getFullYear();

    // --- carrinho (localStorage) ---
    const CART_KEY = "cart_items_v1";
    function getCart() { try { return JSON.parse(localStorage.getItem(CART_KEY)) || {}; } catch { return {}; } }
    function setCart(c) { localStorage.setItem(CART_KEY, JSON.stringify(c)); updateCartCount(); }
    function updateCartCount() {
      const c = getCart();
      const total = Object.values(c).reduce((s, it) => s + it.qty, 0);
      cartCount.textContent = total;
    }

    // --- helpers ---
    const fmtBRL = v => new Intl.NumberFormat("pt-BR", { style: "currency", currency: "BRL"}).format(v);
    function matchesFilter(p, term) {
      term = term.trim().toLowerCase();
      if (!term) return true;
      return (p.nome || "").toLowerCase().includes(term) ||
             (p.id || "").toLowerCase().includes(term);
    }
    function sortItems(arr, mode) {
      const a = [...arr];
      switch (mode) {
        case "preco_asc":  return a.sort((x,y)=> (x.preco ?? 0) - (y.preco ?? 0));
        case "preco_desc": return a.sort((x,y)=> (y.preco ?? 0) - (x.preco ?? 0));
        case "nome_asc":   return a.sort((x,y)=> (x.nome||"").localeCompare(y.nome||""));
        case "nome_desc":  return a.sort((x,y)=> (y.nome||"").localeCompare(x.nome||""));
        default: return a;
      }
    }

    // --- render ---
    function render(products) {
      grid.innerHTML = "";
      const term = q.value;
      const mode = ord.value;
      const filtered = sortItems(products.filter(p => matchesFilter(p, term)), mode);

      if (!filtered.length) {
        grid.hidden = true; emptyEl.hidden = false; return;
      }
      emptyEl.hidden = true; grid.hidden = false;

      for (const p of filtered) {
        const node = cardTmpl.content.cloneNode(true);
        const card = node.querySelector(".card");
        const img = node.querySelector(".thumb");
        const title = node.querySelector(".title");
        const price = node.querySelector(".price");
        const stock = node.querySelector(".stock");
        const btn = node.querySelector(".addBtn");

        img.src = p.urlImagem || "";
        img.alt = p.nome || "Produto";
        title.textContent = p.nome ?? "(Sem nome)";
        price.textContent = fmtBRL(Number(p.preco ?? 0));
        stock.textContent = (p.quantidadeEmEstoque ?? 0) > 0
          ? `• ${p.quantidadeEmEstoque} em estoque`
          : "• indisponível";

        btn.disabled = (p.quantidadeEmEstoque ?? 0) <= 0;
        btn.addEventListener("click", () => {
          const cart = getCart();
          const id = p.id || crypto.randomUUID();
          if (!cart[id]) cart[id] = { id, nome: p.nome, preco: Number(p.preco ?? 0), qty: 0 };
          cart[id].qty = Math.min((cart[id].qty || 0) + 1, Math.max(1, p.quantidadeEmEstoque ?? 1));
          setCart(cart);
          btn.textContent = "Adicionado ✓";
          setTimeout(()=> btn.textContent = "Adicionar ao carrinho", 1000);
        });

        grid.appendChild(node);
      }
    }

    // --- fetch catálogo ---
    let cache = [];
    async function load() {
      try {
        statusEl.textContent = "Carregando catálogo…";
        const res = await fetch(API, { headers: { "Accept": "application/json" }});
        if (!res.ok) throw new Error(`HTTP ${res.status}`);
        const data = await res.json();
        cache = Array.isArray(data) ? data : [];
        statusEl.textContent = `${cache.length} produto(s) carregado(s).`;
        render(cache);
      } catch (err) {
        console.error(err);
        statusEl.hidden = true;
        errorEl.hidden = false;
        errorEl.textContent = "Não foi possível carregar o catálogo. Verifique a API e CORS.";
      } finally {
        updateCartCount();
      }
    }

    // --- eventos de UI ---
    q.addEventListener("input", () => render(cache));
    ord.addEventListener("change", () => render(cache));

    // inicialização
    load();