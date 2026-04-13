package es.madrid.vicinatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WelcomeController {
    @GetMapping(value = "/", produces = "text/html")
    public String welcome(){
        return """
                <html>
                                <body style="font-family: Arial, sans-serif; line-height: 1.6; padding: 40px; max-width: 800px; margin: auto;">
                                    <h1 style="color: #2c3e50;">🚀 Vicinatus API - Manual de Uso</h1>
                                    <p>Bienvenido al backend de la red de intercambio de objetos de Madrid.</p>
                
                                    <h2 style="color: #2980b9;">📋 Entidades del Sistema</h2>
                                    <ul>
                                        <li><strong>Users:</strong> Vecinos con nombre, email y puntos de karma.</li>
                                        <li><strong>Items:</strong> Objetos disponibles (taladros, escaleras, juegos...).</li>
                                        <li><strong>Bookings:</strong> Reservas con fechas de inicio y fin.</li>
                                    </ul>
                
                                    <h2 style="color: #2980b9;">🛣️ Rutas Principales (API Endpoints)</h2>
                                    <table border="1" style="border-collapse: collapse; width: 100%;">
                                        <tr style="background-color: #f2f2f2;">
                                            <th style="padding: 10px;">Método</th>
                                            <th style="padding: 10px;">Ruta</th>
                                            <th style="padding: 10px;">Función</th>
                                        </tr>
                                        <tr>
                                            <td style="padding: 10px;">GET</td>
                                            <td style="padding: 10px;">/api/items</td>
                                            <td style="padding: 10px;">Ver todos los objetos disponibles</td>
                                        </tr>
                                        <tr>
                                            <td style="padding: 10px;">POST</td>
                                            <td style="padding: 10px;">/api/bookings</td>
                                            <td style="padding: 10px;">Solicitar un préstamo</td>
                                        </tr>
                                    </table>
                                </body>
                            </html>
                """;
    }
}
