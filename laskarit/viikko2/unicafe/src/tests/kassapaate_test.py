import unittest
from kassapaate import Kassapaate
from maksukortti import Maksukortti

class TestKassapaate(unittest.TestCase):
    def setUp(self):
        self.paate = Kassapaate()
        self.kortti = Maksukortti(1000)

    def test_konstruktori_asettaa_rahamaaran_oikein(self):
        self.assertEqual(self.paate.kassassa_rahaa, 100000)

    def test_konstruktori_asettaa_myydyt_edulliset_nollaksi(self):
        self.assertEqual(self.paate.edulliset, 0)

    def test_konstruktori_asettaa_myydyt_maukkaat_nollaksi(self):
        self.assertEqual(self.paate.maukkaat, 0)

# Käteisostot, edulliset
    def test_kateistosto_edullinen_maksu_riittava_rahamaara_kasvaa(self):
        self.paate.syo_edullisesti_kateisella(240)
        self.assertEqual(self.paate.kassassa_rahaa, 100240)

    def test_kateistosto_edullinen_maksu_riittava_vaihtoraha_nolla(self):
        self.assertEqual(self.paate.syo_edullisesti_kateisella(240), 0)

    def test_kateistosto_edullinen_maksu_riittava_vaihtoraha_60_senttia(self):
        self.assertEqual(self.paate.syo_edullisesti_kateisella(300), 60)

    def test_kateistosto_edullinen_maksu_riittava_myydyt_kasvavat(self):
        self.paate.syo_edullisesti_kateisella(240)
        self.assertEqual(self.paate.edulliset, 1)

    def test_kateistosto_edullinen_maksu_ei_riittava_rahamaara_ei_kasva(self):
        self.paate.syo_edullisesti_kateisella(200)
        self.assertEqual(self.paate.kassassa_rahaa, 100000)

    def test_kateistosto_edullinen_maksu_ei_riittava_vaihtoraha_kaikki(self):
        self.assertEqual(self.paate.syo_edullisesti_kateisella(200), 200)

    def test_kateistosto_edullinen_maksu_ei_riittava_myydyt_ei_kasva(self):
        self.paate.syo_edullisesti_kateisella(200)
        self.assertEqual(self.paate.edulliset, 0)

# Käteisostot, maukkaat
    def test_kateistosto_maukas_maksu_riittava_rahamaara_kasvaa(self):
        self.paate.syo_maukkaasti_kateisella(400)
        self.assertEqual(self.paate.kassassa_rahaa, 100400)

    def test_kateistosto_maukas_maksu_riittava_vaihtoraha_nolla(self):
        self.assertEqual(self.paate.syo_maukkaasti_kateisella(400), 0)

    def test_kateistosto_maukas_maksu_riittava_vaihtoraha_euro(self):
        self.assertEqual(self.paate.syo_maukkaasti_kateisella(500), 100)

    def test_kateistosto_maukas_maksu_riittava_myydyt_kasvavat(self):
        self.paate.syo_maukkaasti_kateisella(400)
        self.assertEqual(self.paate.maukkaat, 1)

    def test_kateistosto_maukas_maksu_ei_riittava_rahamaara_ei_kasva(self):
        self.paate.syo_maukkaasti_kateisella(395)
        self.assertEqual(self.paate.kassassa_rahaa, 100000)

    def test_kateistosto_maukas_maksu_ei_riittava_vaihtoraha_kaikki(self):
        self.assertEqual(self.paate.syo_maukkaasti_kateisella(395), 395)

    def test_kateistosto_maukas_maksu_ei_riittava_myydyt_ei_kasva(self):
        self.paate.syo_maukkaasti_kateisella(395)
        self.assertEqual(self.paate.maukkaat, 0)

# Korttiostot, edulliset
    def test_korttiosto_edullinen_maksu_riittava_kortti_veloitettu(self):
        self.paate.syo_edullisesti_kortilla(self.kortti)
        self.assertEqual(self.kortti.saldo_euroina(), 7.6)

    def test_korttiosto_edullinen_maksu_riittava_palautus_true(self):
        self.assertTrue(self.paate.syo_edullisesti_kortilla(self.kortti))

    def test_korttiosto_edullinen_maksu_riittava_myydyt_kasvavat(self):
        self.paate.syo_edullisesti_kortilla(self.kortti)
        self.assertEqual(self.paate.edulliset, 1)

    def test_korttiosto_edullinen_maksu_riittava_kassa_ei_kasva(self):
        self.paate.syo_edullisesti_kortilla(self.kortti)
        self.assertEqual(self.paate.kassassa_rahaa, 100000)

    def test_korttiosto_edullinen_maksu_ei_riittava_kortti_ei_veloitettu(self):
        self.kortti.ota_rahaa(800)
        self.paate.syo_edullisesti_kortilla(self.kortti)
        self.assertEqual(self.kortti.saldo_euroina(), 2.0)

    def test_korttiosto_edullinen_maksu_ei_riittava_palautus_false(self):
        self.kortti.ota_rahaa(800)
        self.assertFalse(self.paate.syo_edullisesti_kortilla(self.kortti))

    def test_korttiosto_edullinen_maksu_ei_riittava_myydyt_ei_kasva(self):
        self.kortti.ota_rahaa(800)
        self.paate.syo_edullisesti_kortilla(self.kortti)
        self.assertEqual(self.paate.edulliset, 0)

    def test_korttiosto_edullinen_maksu_ei_riittava_kassa_ei_kasva(self):
        self.kortti.ota_rahaa(800)
        self.paate.syo_edullisesti_kortilla(self.kortti)
        self.assertEqual(self.paate.kassassa_rahaa, 100000)

# Korttiostot, maukkaat
    def test_korttiosto_maukas_maksu_riittava_kortti_veloitettu(self):
        self.paate.syo_maukkaasti_kortilla(self.kortti)
        self.assertEqual(self.kortti.saldo_euroina(), 6.0)

    def test_korttiosto_maukas_maksu_riittava_palautus_true(self):
        self.assertTrue(self.paate.syo_maukkaasti_kortilla(self.kortti))

    def test_korttiosto_maukas_maksu_riittava_myydyt_kasvavat(self):
        self.paate.syo_maukkaasti_kortilla(self.kortti)
        self.assertEqual(self.paate.maukkaat, 1)

    def test_korttiosto_maukas_maksu_riittava_kassa_ei_kasva(self):
        self.paate.syo_maukkaasti_kortilla(self.kortti)
        self.assertEqual(self.paate.kassassa_rahaa, 100000)

    def test_korttiosto_maukas_maksu_ei_riittava_kortti_ei_veloitettu(self):
        self.kortti.ota_rahaa(800)
        self.paate.syo_maukkaasti_kortilla(self.kortti)
        self.assertEqual(self.kortti.saldo_euroina(), 2.0)

    def test_korttiosto_maukas_maksu_ei_riittava_palautus_false(self):
        self.kortti.ota_rahaa(800)
        self.assertFalse(self.paate.syo_maukkaasti_kortilla(self.kortti))

    def test_korttiosto_maukas_maksu_ei_riittava_myydyt_ei_kasva(self):
        self.kortti.ota_rahaa(800)
        self.paate.syo_maukkaasti_kortilla(self.kortti)
        self.assertEqual(self.paate.maukkaat, 0)

    def test_korttiosto_maukas_maksu_ei_riittava_kassa_ei_kasva(self):
        self.kortti.ota_rahaa(800)
        self.paate.syo_maukkaasti_kortilla(self.kortti)
        self.assertEqual(self.paate.kassassa_rahaa, 100000)

# Korttilataukset
    def test_lataa_rahaa_kortille_saldo_muuttuu(self):
        self.paate.lataa_rahaa_kortille(self.kortti, 2000)
        self.assertEqual(self.kortti.saldo_euroina(), 30.0)

    def test_lataa_rahaa_kortille_kassa_muuttuu(self):
        self.paate.lataa_rahaa_kortille(self.kortti, 2000)
        self.assertEqual(self.paate.kassassa_rahaa_euroina(), 1020.0)
