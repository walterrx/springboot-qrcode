package br.com.walterrx.qrcode.example.restservice;

import br.com.walterrx.qrcode.example.business.QRCodeService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping(QRCodeAPI.BASE_URI)
public class QRCodeAPI {

    public final static String BASE_URI = "svc/v1/";
    //private static final String QR_CODE_IMAGE_PATH = "./MyQRCode.png";

    @Autowired
    private QRCodeService qrCodeService;


    /**
     * @author WalterRx
     *
     * Método responsável por expor uma API POST para geracao de QRCode
     * param = message
     *
     */

    @PostMapping(path="qrcode")
    public ResponseEntity<?> consultarIndicadores(@RequestParam("message") String message) {

        String retorno=null;

        try {
            retorno = qrCodeService.getQRCodeImage(message, 350, 350);
        } catch (WriterException e) {
            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
        }

            return new ResponseEntity<>(retorno, HttpStatus.OK);
    }


}
