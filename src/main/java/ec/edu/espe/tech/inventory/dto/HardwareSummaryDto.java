package ec.edu.espe.tech.inventory.dto;

import ec.edu.espe.tech.inventory.enums.HardwareCategory;
import ec.edu.espe.tech.inventory.enums.HardwareStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class HardwareSummaryDto {

    private Long id;
    private String modelo;
    private HardwareCategory categoria;
    private BigDecimal precio;
    private LocalDate fechaCompra;
    private HardwareStatus estado;

    public HardwareSummaryDto() {
    }

    public HardwareSummaryDto(Long id, String modelo, HardwareCategory categoria, BigDecimal precio, LocalDate fechaCompra, HardwareStatus estado) {
        this.id = id;
        this.modelo = modelo;
        this.categoria = categoria;
        this.precio = precio;
        this.fechaCompra = fechaCompra;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public HardwareCategory getCategoria() {
        return categoria;
    }

    public void setCategoria(HardwareCategory categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public HardwareStatus getEstado() {
        return estado;
    }

    public void setEstado(HardwareStatus estado) {
        this.estado = estado;
    }
}
