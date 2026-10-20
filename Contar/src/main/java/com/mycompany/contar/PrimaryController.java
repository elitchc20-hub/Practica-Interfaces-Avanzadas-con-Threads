package com.mycompany.contar;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.application.Platform;

public class PrimaryController {
    
    @FXML private TextField txtConteo;
    @FXML private ProgressBar progressBar;
    @FXML private Button btnContar;   
    @FXML private Button btnEsperar;
    
    private int contador = 0;

    @FXML
    private void btnContar_click() {
        contador++; 
        txtConteo.setText(String.valueOf(contador));
    }
    @FXML
    private void btnEsperar_click() {
        btnEsperar.setDisable(true);
        
        Thread tiempoEspera = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 1; i <= 10; i++) {
                        final double avance = i / 10.0;
                        
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setProgress(avance);
                            }
                        });
                        
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            btnEsperar.setDisable(false);
                        }
                    });
                }
            }
        });
        tiempoEspera.start();
    }
}