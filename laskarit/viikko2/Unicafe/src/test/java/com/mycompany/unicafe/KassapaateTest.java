package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {

    Kassapaate kassapaate;

    public KassapaateTest() {
    }

    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
    }

    @Test
    public void luodunKassapaatteenRahamaaraOikea() {
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void luodunKassapaatteenMaukkaidenMaaraOikein() {
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void luodunKassapaatteenEdullisenMaaraOikein() {
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoRiittavaMaksuEdullinenKassanRahaKasvaa() {
        kassapaate.syoEdullisesti(240);
        assertEquals(100240, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kateisostoRiittavaMaksuMaukasKassanRahaKasvaa() {
        kassapaate.syoMaukkaasti(400);
        assertEquals(100400, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kateisostoRiittavaMaksuEdullinenVaihtorahaOikea() {
        assertEquals(1, kassapaate.syoEdullisesti(241));
    }
    
    @Test
    public void kateisostoRiittavaMaksuMaukasVaihtorahaOikea() {
        assertEquals(1, kassapaate.syoMaukkaasti(401));
    }
    
    @Test
    public void kateisostoRiittavaMaksuEdullinenLounaidenMaaraKasvaa() {
        kassapaate.syoEdullisesti(240);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoRiittavaMaksuMaukasLounaidenMaaraKasvaa() {
        kassapaate.syoMaukkaasti(400);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoEiRiittavaMaksuEdullinenKassanRahaEiMuutu() {
        kassapaate.syoEdullisesti(239);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kateisostoEiRiittavaMaksuMaukasKassanRahaEiMuutu() {
        kassapaate.syoMaukkaasti(399);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kateisostoEiRiittavaMaksuEdullinenRahatPalautetaan() {
        assertEquals(239, kassapaate.syoEdullisesti(239));
    }
    
    @Test
    public void kateisostoEiRiittavaMaksuMaukasRahatPalautetaan() {
        assertEquals(399, kassapaate.syoMaukkaasti(399));
    }
    
    @Test
    public void kateisostoEiRiittavaMaksuEdullinenLounaatEiKasva() {
        kassapaate.syoEdullisesti(239);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoEiRiittavaMaksuMaukasLounaatEiKasva() {
        kassapaate.syoMaukkaasti(399);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoRiittavaMaksuEdullinenVeloitusOikein() {
        Maksukortti kortti = new Maksukortti(240);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(0, kortti.saldo());
    }
    
    @Test
    public void korttiostoRiittavaMaksuMaukasVeloitusOikein() {
        Maksukortti kortti = new Maksukortti(400);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(0, kortti.saldo());
    }
    
    @Test
    public void korttiostoRiittavaMaksuEdullinenPalautusTrue() {
        Maksukortti kortti = new Maksukortti(240);
        assertEquals(true, kassapaate.syoEdullisesti(kortti));
    }
    
    @Test
    public void korttiostoRiittavaMaksuMaukasPalautusTrue() {
        Maksukortti kortti = new Maksukortti(400);
        assertEquals(true, kassapaate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void korttiostoRiittavaMaksuEdullinenLounaatKasvaa() {
        kassapaate.syoEdullisesti(new Maksukortti(240));
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoRiittavaMaksuMaukasLounaatKasvaa() {
        kassapaate.syoMaukkaasti(new Maksukortti(400));
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoRiittavaMaksuEdullinenKassanRahaKasvaa() {
        kassapaate.syoEdullisesti(new Maksukortti(240));
        assertEquals(100240, kassapaate.kassassaRahaa());
    }

    @Test
    public void korttiostoRiittavaMaksuMaukasKassanRahaKasvaa() {
        kassapaate.syoMaukkaasti(new Maksukortti(400));
        assertEquals(100400, kassapaate.kassassaRahaa());
    }

    @Test
    public void korttiostoEiRahaaEdullinenSaldoEiMuutu() {
        Maksukortti kortti = new Maksukortti(239);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(239, kortti.saldo());
    }
    
    @Test
    public void korttiostoEiRahaaMaukasSaldoEiMuutu() {
        Maksukortti kortti = new Maksukortti(399);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(399, kortti.saldo());
    }
    
    @Test
    public void korttiostoEiRahaaEdullinenLounaatEiMuutu() {
        kassapaate.syoEdullisesti(new Maksukortti(239));
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoEiRahaaMaukasLounaatEiMuutu() {
        kassapaate.syoMaukkaasti(new Maksukortti(399));
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoEiRahaaEdullinenPalautetaanFalse() {
        assertEquals(false, kassapaate.syoEdullisesti(new Maksukortti(239)));
    }
    
    @Test
    public void korttiostoEiRahaaMaukasPalautetaanFalse() {
        assertEquals(false, kassapaate.syoMaukkaasti(new Maksukortti(399)));
    }
    
    @Test
    public void korttiostoEiRahaaEdullinenKassanRahaEiKasva() {
        kassapaate.syoEdullisesti(new Maksukortti(239));
        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void korttiostoEiRahaaMaukasKassanRahaEiKasva() {
        kassapaate.syoMaukkaasti(new Maksukortti(399));
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void korttilatausSaldoKasvaa() {
        // Miksi kassapäätteen rahamäärä pitäisi kasvaa, kun ladataan korttia?
        // Sehän on älytöntä. Korttihan on täysin erillään kassapäätteestä,
        // ja siitä veloitetaan, kun ostetaan jotain, eikö vain? En tarkasta
        // kassapäätteen rahamäärää tässä.
        Maksukortti kortti = new Maksukortti(100);
        kortti.lataaRahaa(500);
        assertEquals(600, kortti.saldo());
    }
    
    @Test
    public void testaaKassapaatteenLataaRahaaKortilleSaldoKasvaa() {
        // Aijahas, oli tällainen metodi siellä. Aika kummallinen. No testataan
        // sitten.
        Maksukortti kortti = new Maksukortti(100);
        kassapaate.lataaRahaaKortille(kortti, 500);
        assertEquals(600, kortti.saldo());
    }
    
    @Test
    public void testaaKassapaatteenLataaRahaaKortilleKassanRahamaaraKasvaa() {
        // Tuo maksukortin alkusaldo (täällä 100 senttiä) ei muuten näy
        // kassapäätteessä. Kortithan pitäisi alustaa kassapäätteen kautta, jos
        // halutaan että kortin saldo sijaitsee koko ajan kassapäätteellä.
        // Mutta se on silti älytöntä. Mitä jos esim. hankitaan uusi kassapääte?
        // Niillä pitäisi sitten olla yhteinen rahakassi, tai sitten sallitaan,
        // että kassan rahamäärä voi mennä miinukselle.
        Maksukortti kortti = new Maksukortti(100);
        kassapaate.lataaRahaaKortille(kortti, 500);
        assertEquals(100500, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void testaaKassapaatteenLataaRahaaKortilleMinusRahallaSaldoEiKasva() {
        Maksukortti kortti = new Maksukortti(100);
        kassapaate.lataaRahaaKortille(kortti, -500);
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void testaaKassapaatteenLataaRahaaKortilleMiinusRahallaKassanRahamaaraEiKasva() {
        Maksukortti kortti = new Maksukortti(100);
        kassapaate.lataaRahaaKortille(kortti, -500);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
}
