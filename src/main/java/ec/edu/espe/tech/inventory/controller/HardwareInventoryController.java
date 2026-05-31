package ec.edu.espe.tech.inventory.controller;

import ec.edu.espe.tech.inventory.dto.HardwareReportDto;
import ec.edu.espe.tech.inventory.dto.InventoryResponseDto;
import ec.edu.espe.tech.inventory.service.AiService;
import ec.edu.espe.tech.inventory.service.HardwareInventoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class HardwareInventoryController {

    private final HardwareInventoryService hardwareInventoryService;
    private final AiService aiService;

    public HardwareInventoryController(HardwareInventoryService hardwareInventoryService, AiService aiService) {
        this.hardwareInventoryService = hardwareInventoryService;
        this.aiService = aiService;
    }

    @GetMapping("/imperative")
    public InventoryResponseDto obtenerReporteImperativo() {
        List<HardwareReportDto> reportes = hardwareInventoryService.generarReporteImperativo();
        return new InventoryResponseDto("Imperativo", aiService.generarResumenInventario(reportes), reportes);
    }

    @GetMapping("/functional")
    public InventoryResponseDto obtenerReporteFuncional() {
        List<HardwareReportDto> reportes = hardwareInventoryService.generarReporteFuncional();
        return new InventoryResponseDto("Funcional", aiService.generarResumenInventario(reportes), reportes);
    }
}
