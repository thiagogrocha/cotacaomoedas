package br.com.trochadev.cotacaomoedas.service;

import br.com.trochadev.cotacaomoedas.entity.Holiday;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@ApplicationScoped
public class BusinessDayService {

    final Logger log = LoggerFactory.getLogger(BusinessDayService.class);

    @Inject
    HolidayService service;

    private String data;

    private String fullData;

    private boolean isBusinessDay;

    public void setDate(String date) {
        this.isBusinessDay(date);
    }

    public String getDay() {
        return this.fullData;
    }

    public boolean isBusinessDay() {
        return isBusinessDay;
    }


    private void isBusinessDay(String data) {

        Optional<Holiday> holidayOpt = service.getHoliday(data);

        DayOfWeek dow = LocalDate.parse(data.replace("'",""), DateTimeFormatter.ofPattern("MM-dd-yyyy")).getDayOfWeek();

        if (holidayOpt.isPresent()) {
            this.isBusinessDay = false;
            this.fullData = "Feriado: " + holidayOpt.get().getName() +
                    " - Data: " + holidayOpt.get().getDate();
        } else if (dow == DayOfWeek.SATURDAY) {
            this.isBusinessDay = false;
            this.fullData = "Sábado.";
        } else if (dow == DayOfWeek.SUNDAY) {
            this.isBusinessDay = false;
            this.fullData = "Domingo.";
        } else
            this.isBusinessDay = true;
    }
}
