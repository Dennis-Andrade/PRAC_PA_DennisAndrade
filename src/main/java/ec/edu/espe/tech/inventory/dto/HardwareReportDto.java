package ec.edu.espe.tech.inventory.dto;

import ec.edu.espe.tech.inventory.enums.HardwareCategory;

import java.math.BigDecimal;

public class HardwareReportDto {

    private HardwareCategory categoria;
    private BigDecimal valorTotal;
    private BigDecimal precioPromedio;
    private HardwareSummaryDto equipoMasCaro;

    public HardwareReportDto() {
    }

    public HardwareReportDto(HardwareCategory categoria, BigDecimal valorTotal, BigDecimal precioPromedio, HardwareSummaryDto equipoMasCaro) {
        this.categoria = categoria;
        this.valorTotal = valorTotal;
        this.precioPromedio = precioPromedio;
        this.equipoMasCaro = equipoMasCaro;
    }

    public HardwareCategory getCategoria() {
        return categoria;
    }

    public void setCategoria(HardwareCategory categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getPrecioPromedio() {
        return precioPromedio;
    }

    public void setPrecioPromedio(BigDecimal precioPromedio) {
        this.precioPromedio = precioPromedio;
    }

    public HardwareSummaryDto getEquipoMasCaro() {
        return equipoMasCaro;
    }

    public void setEquipoMasCaro(HardwareSummaryDto equipoMasCaro) {
        this.equipoMasCaro = equipoMasCaro;
    }
}
