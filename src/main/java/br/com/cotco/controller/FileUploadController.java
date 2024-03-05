package br.com.cotco.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public String uploadRealizado(@RequestParam("arquivo")MultipartFile arquivo,
                                  @RequestParam("nomeProduto") String nomeProduto) {

        StringBuilder resultado;
        try {
            //salvar em um local temporário
            File tempFile = File.createTempFile("temp", ".csv");
            arquivo.transferTo(tempFile);

            //Executa o script py com arquivo recebido
            ProcessBuilder processBuilder = new ProcessBuilder("python", "C:\\Users\\Luiz_\\Documents\\ANALISE DEV DE SISTEMAS\\COT&CO\\AI_ANALITYCS\\cotcoAI.py", tempFile.getAbsolutePath(), nomeProduto);
            Process process = processBuilder.start();

            //Captura a saída do script py
            BufferedReader leitor = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            resultado = new StringBuilder();

            while ((line = leitor.readLine()) != null) {
                resultado.append(String.valueOf(line));
            }

            process.waitFor();

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(resultado.toString(), JsonObject.class);
            if (jsonObject.has("message")) {
                System.out.println(jsonObject.get("message").getAsString());
            } else {
                String nomeProdutoresultado = jsonObject.get("name").getAsString();
                double valorProduto = jsonObject.get("actual_price").getAsDouble();
                System.out.println("Nome pesquisado: " + nomeProduto + "'");
                System.out.println("--------------------");
                System.out.println("Nome do produto encontrado: " + nomeProdutoresultado);
                System.out.println("Valor do produto: R$" + valorProduto);
            }


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Erro ao processar o arquivo.";
        }
        return resultado.toString();
    }
}
