package ec.edu.espe.tech.inventory.service;

import ec.edu.espe.tech.inventory.dto.HardwareReportDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AiService {

    public String generarResumenInventario(List<HardwareReportDto> reportes) {
        int categorias = reportes.size();
        BigDecimal valorTotal = reportes.stream()
                .map(HardwareReportDto::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return "Resumen de Inventario: se analizaron " + categorias + " categorias activas con un valor total de " + valorTotal + ".";
    }
}
