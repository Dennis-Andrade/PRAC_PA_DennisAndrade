package ec.edu.espe.tech.inventory.service;

import ec.edu.espe.tech.inventory.dto.HardwareReportDto;
import ec.edu.espe.tech.inventory.dto.HardwareSummaryDto;
import ec.edu.espe.tech.inventory.entity.HardwareEntity;
import ec.edu.espe.tech.inventory.enums.HardwareCategory;
import ec.edu.espe.tech.inventory.enums.HardwareStatus;
import ec.edu.espe.tech.inventory.repository.HardwareRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HardwareInventoryService {

    private final HardwareRepository hardwareRepository;

    public HardwareInventoryService(HardwareRepository hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }

    public List<HardwareReportDto> generarReporteImperativo() {
        List<HardwareEntity> hardwareList = hardwareRepository.findAll();
        LocalDate fechaMinima = LocalDate.now().minusYears(5);
        Map<HardwareCategory, BigDecimal> totales = new EnumMap<>(HardwareCategory.class);
        Map<HardwareCategory, Integer> cantidades = new EnumMap<>(HardwareCategory.class);
        Map<HardwareCategory, HardwareEntity> equiposMasCaros = new EnumMap<>(HardwareCategory.class);

        for (HardwareEntity hardware : hardwareList) {
            if (hardware.getEstado() == HardwareStatus.ACTIVO && !hardware.getFechaCompra().isBefore(fechaMinima)) {
                HardwareCategory categoria = hardware.getCategoria();
                BigDecimal totalActual = totales.get(categoria);
                Integer cantidadActual = cantidades.get(categoria);
                HardwareEntity equipoMasCaroActual = equiposMasCaros.get(categoria);

                if (totalActual == null) {
                    totalActual = BigDecimal.ZERO;
                }

                if (cantidadActual == null) {
                    cantidadActual = 0;
                }

                totales.put(categoria, totalActual.add(hardware.getPrecio()));
                cantidades.put(categoria, cantidadActual + 1);

                if (equipoMasCaroActual == null || hardware.getPrecio().compareTo(equipoMasCaroActual.getPrecio()) > 0) {
                    equiposMasCaros.put(categoria, hardware);
                }
            }
        }

        List<HardwareReportDto> reportes = new ArrayList<>();

        for (HardwareCategory categoria : HardwareCategory.values()) {
            BigDecimal total = totales.get(categoria);
            Integer cantidad = cantidades.get(categoria);

            if (total != null && cantidad != null && cantidad > 0) {
                BigDecimal promedio = total.divide(BigDecimal.valueOf(cantidad), 2, RoundingMode.HALF_UP);
                reportes.add(new HardwareReportDto(categoria, total, promedio, convertirResumen(equiposMasCaros.get(categoria))));
            }
        }

        return reportes;
    }

    public List<HardwareReportDto> generarReporteFuncional() {
        LocalDate fechaMinima = LocalDate.now().minusYears(5);
        List<HardwareEntity> hardwareFiltrado = hardwareRepository.findAll()
                .stream()
                .filter(hardware -> hardware.getEstado() == HardwareStatus.ACTIVO)
                .filter(hardware -> !hardware.getFechaCompra().isBefore(fechaMinima))
                .toList();

        Map<HardwareCategory, List<HardwareEntity>> agrupados = hardwareFiltrado.stream()
                .collect(Collectors.groupingBy(HardwareEntity::getCategoria));

        return agrupados.entrySet()
                .stream()
                .map(entry -> {
                    BigDecimal total = entry.getValue()
                            .stream()
                            .map(HardwareEntity::getPrecio)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    BigDecimal promedio = total.divide(BigDecimal.valueOf(entry.getValue().size()), 2, RoundingMode.HALF_UP);
                    Optional<HardwareEntity> equipoMasCaro = entry.getValue()
                            .stream()
                            .max(Comparator.comparing(HardwareEntity::getPrecio));

                    return new HardwareReportDto(entry.getKey(), total, promedio, equipoMasCaro.map(this::convertirResumen).orElse(null));
                })
                .sorted(Comparator.comparing(report -> report.getCategoria().name()))
                .toList();
    }

    private HardwareSummaryDto convertirResumen(HardwareEntity hardware) {
        if (hardware == null) {
            return null;
        }

        return new HardwareSummaryDto(
                hardware.getId(),
                hardware.getModelo(),
                hardware.getCategoria(),
                hardware.getPrecio(),
                hardware.getFechaCompra(),
                hardware.getEstado()
        );
    }
}
