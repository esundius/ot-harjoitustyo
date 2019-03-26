package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void alkuSaldoOikein() {
        assertEquals(10, kortti.saldo());
    }

    @Test
    public void saldonLatausToimii() {
        kortti.lataaRahaa(25);
        assertEquals(35, kortti.saldo());
    }

    @Test
    public void saldoVaheneeJosRahaaTarpeeksi() {
        kortti.otaRahaa(10);
        assertEquals(0, kortti.saldo());
    }

    @Test
    public void saldoEiMuutuJosEiTarpeeksiRahaa() {
        kortti.otaRahaa(100);
        assertEquals(10, kortti.saldo());
    }

    @Test
    public void otaRahaaTrueJosRahatRiittivat() {
        assertEquals(true, kortti.otaRahaa(10));
    }

    @Test
    public void otaRahaaFalseJosRahatEiRiita() {
        assertEquals(false, kortti.otaRahaa(11));
    }
    
    @Test
    public void toStringToimii() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
}
