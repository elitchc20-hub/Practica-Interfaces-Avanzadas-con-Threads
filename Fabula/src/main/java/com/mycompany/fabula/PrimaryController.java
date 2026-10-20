package com.mycompany.fabula;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

public class PrimaryController {
    
    @FXML Label lblEstadoLiebre;   
    @FXML Label lblEstadoTortuga;   
    @FXML private ProgressBar progressIniciandoLiebre;   
    @FXML private ProgressBar progressIniciandoTortuga;     
    @FXML Button btnIniciar; 
    
    @FXML
    private void btnIniciar_click () throws IOException {
        
        btnIniciar.setDisable(true);
        Thread hiloTortuga = new Thread(new Runnable() {
        
        @Override
        public void run(){
                try {
                    for(double i = 0.0; i <= 1.2; i = i + 0.04){
                        final double avanceTortuga = i;
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run(){
                                progressIniciandoTortuga.setProgress(avanceTortuga);
                            }
                        });
                        Thread.sleep(240);
                    }
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run(){
                            lblEstadoTortuga.setText("¡La tortuga gano la carrera!");
                        }
                    });
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }             
        });
        
        Thread hiloLiebre = new Thread(new Runnable(){
            @Override
            public void run(){
                try {
                   double avanceLiebre = 0.0;
                    while(avanceLiebre < 1.0){
                        if(avanceLiebre < 0.4){
                            avanceLiebre += 0.08;
                            final double pogreso = avanceLiebre;
                            
                            Platform.runLater(new Runnable(){
                                @Override
                                public void run(){
                                    progressIniciandoLiebre.setProgress(pogreso);
                                    lblEstadoLiebre.setText("La liebre: ¡Esta corriendo!");
                                }
                            });
                            Thread.sleep(150);
                        } 
                        else if(avanceLiebre >= 0.4 && avanceLiebre < 0.45){
                            Platform.runLater(new Runnable(){
                                @Override
                                public void run(){
                                    lblEstadoLiebre.setText("La liebre: ¡Se durmió!");
                                }
                            });
                            Thread.sleep(5000);
                            avanceLiebre += 0.08;
                            final double pogreso = avanceLiebre;
                            
                            Platform.runLater(new Runnable(){
                                @Override
                                public void run(){
                                    progressIniciandoLiebre.setProgress(Math.min(pogreso, 1.0));
                                    lblEstadoLiebre.setText("La liebre: ¡Despertó y ya esta corriendo!");
                                }
                            });
                            Thread.sleep(140);
                        }
                        else{
                            avanceLiebre += 0.08;
                            final double pogreso = avanceLiebre;
                            Platform.runLater(new Runnable(){
                            @Override
                            public void run(){
                                progressIniciandoLiebre.setProgress(Math.min(pogreso, 1.0));
                                lblEstadoLiebre.setText("La liebre: ¡Corre hacia la meta!");
                            }
                            });
                            Thread.sleep(180);
                        }
                    }
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        hiloTortuga.start();
        hiloLiebre.start();
    }
}