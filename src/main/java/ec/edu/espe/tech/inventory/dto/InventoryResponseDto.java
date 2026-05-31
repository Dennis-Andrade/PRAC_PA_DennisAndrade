package ec.edu.espe.tech.inventory.dto;

import java.util.List;

public class InventoryResponseDto {

    private String enfoque;
    private String resumenInventario;
    private List<HardwareReportDto> reportes;

    public InventoryResponseDto() {
    }

    public InventoryResponseDto(String enfoque, String resumenInventario, List<HardwareReportDto> reportes) {
        this.enfoque = enfoque;
        this.resumenInventario = resumenInventario;
        this.reportes = reportes;
    }

    public String getEnfoque() {
        return enfoque;
    }

    public void setEnfoque(String enfoque) {
        this.enfoque = enfoque;
    }

    public String getResumenInventario() {
        return resumenInventario;
    }

    public void setResumenInventario(String resumenInventario) {
        this.resumenInventario = resumenInventario;
    }

    public List<HardwareReportDto> getReportes() {
        return reportes;
    }

    public void setReportes(List<HardwareReportDto> reportes) {
        this.reportes = reportes;
    }
}
