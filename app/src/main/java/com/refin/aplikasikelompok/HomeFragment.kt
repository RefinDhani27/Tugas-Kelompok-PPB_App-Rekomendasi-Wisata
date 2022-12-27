package com.refin.aplikasikelompok

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import com.refin.aplikasikelompok.adapter.HomeAdapter
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    lateinit var listContent:ArrayList<HomeItem>

    private fun DataTeman() {
        listContent = ArrayList()
        listContent.add(HomeItem("Candi Singasari",
            "Candi Singasari merupakan candi Hindu - Buddha peninggalan bersejarah dari Kerajaan Singasari " +
                    "yang berlokasi di Kelurahan Candirenggo, Kecamatan Singosari, Kabupaten Malang, Jawa Timur, Indonesia, " +
                    "sekitar 10 km dari Kota Malang. Candi ini merupakan tempat pendharmaan bagi raja Singhasari terakhir, Kertanegara, " +
                    "yang meninggal pada tahun 1292. Candi ini berada pada lembah di antara Pegunungan Tengger dan Gunung Arjuno " +
                    "pada ketinggian 512m di atas permukaan laut.",
            R.drawable.gambar_1))
        listContent.add(HomeItem("Coban Rondo",
            "Coban Rondo terletak pada ketinggian 1135 m di atas permukaan laut. " +
                    "Air terjun ini memiliki ketinggian 84 meter dengan debit air berkisar antara 90 liter " +
                    "per detik pada musim kemarau, sampai dengan 150 liter per detik pada musim penghujan. " +
                    "Coban Rondo sebenarnya merupakan bagian dari kelompok air terjun bertingkat " +
                    "(dimulai dengan air terjun kembar bernama Coban Manten yang bergabung menjadi satu dinamakan Coban Dudo, " +
                    "dan kemudian mengalir ke bawah dengan nama Coban Rondo). ",
            R.drawable.gambar_2))
        listContent.add(HomeItem("Pantai Balekambang",
            "Pantai Balekambang adalah sebuah pantai di pesisir selatan yang terletak di tepi Samudra Indonesia " +
                    "secara administratif masuk wilayah Dusun Sumber Jambe, Desa Srigonco, Kecamatan Bantur, Kabupaten Malang, Jawa Timur " +
                    "dan merupakan salah satu wisata di Kabupaten Malang sejak 1985 hingga kini. Daya tarik Balekambang utamanya tentu " +
                    "panorama alam, gelombang ombak yang memanjang hampir dua kilometer, serta hamparan pasir nan luas. " +
                    "Area pasir putih terlihat bersih dari sampah maupun kotoran sehingga cukup nyaman bagi pengunjung untuk " +
                    "bermain dan berolahraga. Bahkan tak jarang di pantai ini menjadi tempat latihan sejumlah klub sepak bola seperti Arema dan Persema.",
            R.drawable.gambar_3))
        listContent.add(HomeItem("Pantai Goa Cina",
            "Pantai Goa Cina adalah sebuah pantai di pesisir selatan yang terletak di Dusun Tumpak Awu" +
                    ", Desa Sitiarjo, Kecamatan Sumbermanjing Wetan, Kabupaten Malang, Jawa Timur. " +
                    "Nama asli dari pantai ini adalah Pantai Rowo Indah. Namun karena pernah terjadi peristiwa " +
                    "kematian seorang Tionghoa yang sedang bertapa di dalam goa yang ada di kawasan pantai ini, " +
                    "nama Rowo Indah diganti dengan Goa Cina karena lebih umum di masyarakat. Tidak ada catatan resmi " +
                    "tahun berapa tragedi itu terjadi, tetapi warga sekitar pantai meyakini sekitar 20 tahunan silam. " +
                    "Dari Pantai Bajulmati, Desa Gajahrejo, Kecamatan Gedangan menuju Pantai Goa Cina ini " +
                    "hanya perlu waktu 15 menit saja karena kedua pantai ini hanya berjarak kurang dari 7 km.",
            R.drawable.gambar_4))
        listContent.add(HomeItem("Pulau Sempu",
            "Pulau Sempu adalah sebuah pulau kecil yang terletak di sebelah selatan Pulau Jawa" +
                    "secara administratif berada di Desa Tambakrejo, Kecamatan Sumbermanjing Wetan, Kabupaten Malang, Jawa Timur. " +
                    "Pulau yang ditumbuhi pepohonan tropis ini adalah cagar alam yang dikelola oleh Balai Besar Konservasi Sumber Daya Alam (BBKSDA) " +
                    "Jawa Timur di bawah KLHK RI. Secara resmi tempat ini ditetapkan sebagai cagar alam sejak 1928 pada masa pemerintahan Hindia Belanda",
            R.drawable.gambar_5))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun tampilContent() {
        rv_content.layoutManager=LinearLayoutManager(activity)
        rv_content.adapter=HomeAdapter(requireActivity(), listContent)
    }

    private fun tampilRv() {
        DataTeman()
        tampilContent()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tampilRv()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}