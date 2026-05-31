const imperativeButton = document.getElementById("imperativeButton");
const functionalButton = document.getElementById("functionalButton");
const summaryText = document.getElementById("summaryText");
const methodText = document.getElementById("methodText");
const categoryText = document.getElementById("categoryText");
const totalText = document.getElementById("totalText");
const stateText = document.getElementById("stateText");
const reportsGrid = document.getElementById("reportsGrid");

const moneyFormatter = new Intl.NumberFormat("es-EC", {
    style: "currency",
    currency: "USD"
});

imperativeButton.addEventListener("click", () => loadReport("/api/inventory/imperative"));
functionalButton.addEventListener("click", () => loadReport("/api/inventory/functional"));

async function loadReport(url) {
    setLoading(true);

    try {
        const response = await fetch(url);
        const data = await response.json();
        renderReport(data);
    } catch (error) {
        summaryText.textContent = "No se pudo cargar el reporte.";
        stateText.textContent = "Verifica que el servidor este activo.";
    } finally {
        setLoading(false);
    }
}

function setLoading(value) {
    document.body.classList.toggle("loading", value);
    imperativeButton.disabled = value;
    functionalButton.disabled = value;
    stateText.textContent = value ? "Generando reporte..." : "Reporte actualizado.";
}

function renderReport(data) {
    const total = data.reportes.reduce((sum, item) => sum + Number(item.valorTotal), 0);

    summaryText.textContent = data.resumenInventario;
    methodText.textContent = data.enfoque;
    categoryText.textContent = data.reportes.length;
    totalText.textContent = moneyFormatter.format(total);
    reportsGrid.innerHTML = "";

    data.reportes.forEach((report) => {
        const card = document.createElement("article");
        card.className = "report-card";
        card.innerHTML = `
            <h3>${report.categoria}</h3>
            <div class="metric">
                <span>Valor total</span>
                <strong>${moneyFormatter.format(Number(report.valorTotal))}</strong>
            </div>
            <div class="metric">
                <span>Precio promedio</span>
                <strong>${moneyFormatter.format(Number(report.precioPromedio))}</strong>
            </div>
            <div class="expensive-item">
                <span>Equipo mas caro</span>
                <strong>${report.equipoMasCaro.modelo}</strong>
                <span>${moneyFormatter.format(Number(report.equipoMasCaro.precio))}</span>
            </div>
        `;
        reportsGrid.appendChild(card);
    });
}
