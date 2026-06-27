package com.utn.climalert.notificador;

import com.utn.climalert.entity.WeatherRecord;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class NotificarEmail {

    private final JavaMailSender mailSender;

    public NotificarEmail(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarAlerta(WeatherRecord clima) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        String[] destinatarios = {
                "admin@clima.com",
                "emergencia@clima.com",
                "meteorologia@clima.com"
        };
        mensaje.setTo(destinatarios);
        mensaje.setSubject("ALERTA METEOROLOGICA");

        DateTimeFormatter formatoNormal = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaNormal = clima.getTimestamp().format(formatoNormal);
        String cuerpoMensaje = String.format(
                "Detalle de clima registrado: \n" +
                        "Temperatura: " + clima.getTemperature() + "°C \n" +
                        "Humedad: " + clima.getHumidity() + "\n"+
                        "Fecha y hora: " + fechaNormal
        );
        mensaje.setText(cuerpoMensaje);
        try {
            mailSender.send(mensaje);
            System.out.println("alerta enviada a las entidades");
        } catch (Exception e ) {
            System.err.println("Error al enviar correo");
        }
    }
}
