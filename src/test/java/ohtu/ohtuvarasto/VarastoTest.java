package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void eiPystyLaittamaanLiikaa() {
        varasto.lisaaVarastoon(8);
        varasto.lisaaVarastoon(4);
        assertEquals(0.0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void eiPystyOttamaanLisää() {
        varasto.lisaaVarastoon(8);
        double saatuMaara = varasto.otaVarastosta(10);
        assertEquals(8, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void tyhjanVarastonLuominen() {
        Varasto var = new Varasto(0.0);
        assertEquals(0.0, var.paljonkoMahtuu(), vertailuTarkkuus);
        assertEquals(0.0, var.getSaldo(), vertailuTarkkuus);
    }
    
    @Test 
    public void konstuktoriSaldollaTyhjaVarasto() {
        Varasto var = new Varasto(0, 0);
        assertEquals(0.0, var.paljonkoMahtuu(), vertailuTarkkuus);
        assertEquals(0.0, var.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void alkuSaldoPienempiKuinTilavuus() {
        Varasto var = new Varasto(5, 4);
        assertEquals(4, var.getSaldo(), vertailuTarkkuus);
        assertEquals(1, var.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test 
    public void alkuSaldoSuurempiKuinTilavuus() {
        Varasto var = new Varasto(4, 5);
        assertEquals(4, var.getSaldo(), vertailuTarkkuus);
        assertEquals(0, var.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenSaldoLuoTyhjanSaldon() {
        Varasto var = new Varasto(5, -1);
        assertEquals(0, var.getSaldo(), vertailuTarkkuus);
        assertEquals(5, var.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttoPalauttaaNollan() {
        double palautus = varasto.otaVarastosta(-1);
        assertEquals(0, palautus, vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisaysEiTeeMitaan() {
        varasto.lisaaVarastoon(-1);
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringMetodiToimii() {
        String oikea = "saldo = 0.0, vielä tilaa 10.0";
        String testi = varasto.toString();
        assertEquals(oikea, testi);
    }
    
    

}