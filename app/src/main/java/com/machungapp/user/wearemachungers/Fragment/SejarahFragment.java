package com.machungapp.user.wearemachungers.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bluejamesbond.text.DocumentView;
import com.bumptech.glide.Glide;
import com.machungapp.user.wearemachungers.R;

public class SejarahFragment extends Fragment {
    private View view;
    private ImageView imgCoverSejarah;
    private DocumentView dvSejarah;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sejarah_machung, container, false);

        getActivity().setTitle("About Ma Chung");

        imgCoverSejarah = view.findViewById(R.id.imgCoverSejarah);
        dvSejarah = view.findViewById(R.id.dvSejarahMachung);

        Glide.with(view.getContext())
                .load("https://hoteldekatkampus.com/wp-content/uploads/2014/10/universitas-ma-chung.jpg")
                .crossFade()
                .into(imgCoverSejarah);

        dvSejarah.setText("Ma Chung merupakan sebuah sekolah bersejarah di kota Malang - Jawa Timur, yang telah meluluskan alumni-alumni terbaiknya sejak era tahun 1950an. Sekolah ini telah mewariskan standar pendidikan dan pembangunan nilai-nilai moral yang konsisten terhadap lulusannya. Bekas gedung sekolah Ma Chung masih dapat disaksikan oleh generasi masa kini meski saat ini digunakan oleh institusi lain, namun satu hal yang tidak akan lekang oleh jaman: Spirit Alumni Ma Chung.\n" +
                "\n" +
                "Tersebar di hampir seluruh penjuru dunia, para alumni ini telah menorehkan sejarah, baik sebagai ilmuwan di universitas-universitas ternama di dunia maupun sebagai wirausahawan Indonesia yang sukses dan bahkan berkiprah di dunia bisnis internasional.\n" +
                "\n" +
                "Ide pendirian Universitas Ma Chung dicetuskan pada saat pelaksanaan Reuni Akbar peringatan hari ulang tahun ke-55 sekolah Ma Chung pada September 2001 di kota Xiamen, China, yang dilandasi oleh warisan semangat Ma Chung yang berintikan: rukun, bersatu, mengabdi kepada masyarakat, serta mewujudkan dedikasi kepada dunia pendidikan Indonesia.\n" +
                "\n" +
                "Dengan dipegang teguhnya semboyan \"Waktu minum air jangan lupa sumbernya, waktu sukses balaslah budi kepada kampung halamannya,\" serta komitmen alumni Ma Chung di seluruh dunia, maka pada 1 Mei 2004 didirikanlah PT. Ma Chung sebagai langkah awal berdirinya Universitas Ma Chung, dipelopori oleh Soegeng Hendarto, Mochtar Riady, Teguh Kinarto, Hendro Sunjoto, Koentjoro Loekito, Effendy Sudargo, Agus Chandra, Hadi Widjojo, Nuryati Tanuwidjaya, Nehemja, Alex Lesmana Samudra, Evelyn Adam, Hadi Surjono, Nagawidjaja Winoto, dan Soebroto Wirotomo - nama-nama yang sudah terkenal sebagai pebisnis berskala internasional.\n" +
                "\n" +
                "Secara aklamasi dan dengan pernyataan kebulatan tekad alumni dari seluruh dunia, dalam Reuni Akbar peringatan Ulang Tahun ke-60 SMA Ma Chung di Malang, 17 Juli 2005 diletakkan batu pertama pembangunan Universitas Ma Chung. Dalam rangka memperlancar jalannya pengelolaan universitas dalam jangka panjang maka dibentuklah Yayasan Harapan Bangsa Sejahtera yang menaungi Universitas Ma Chung.\n" +
                "\n" +
                "Alumni senior yaitu Prof. Dr. Yang Zhiling dan Prof. Dr. Bin Ling memberikan banyak usulan sehubungan dengan pembangunan dan pengelolaan universitas. Usulan beliau tersebut kemudian dijadikan pijakan pertama bagi perencanaan (blue print) oleh para pimpinan PT. Ma Chung dan Yayasan Harapan Bangsa Sejahtera.\n" +
                "\n" +
                "Dihadiri oleh ribuan alumni, pada tanggal 7 Juli 2007, Universitas Ma Chung diresmikan operasionalnya, dan sejak saat itu, Universitas Ma Chung telah mendidik ribuan putra-putri terbaik bangsa untuk menjadi pemimpin masa depan. Tepat empat tahun setelahnya pada 7 Juli 2011, Universitas Ma Chung meluluskan wisudawan-wisudawati terbaik dengan kompetensi unggul. Para lulusan telah membuktikan dirinya dengan menjadi pemimpin dan menorehkan prestasi - baik di bidang bisnis maupun dalam akademik - di perusahaan-perusahaan nasional dan multinasional, badan-badan pemerintah, usaha kecil dan menengah milik pribadi, di berbagai perguruan tinggi di dalam dan luar negeri.");

        return view;
    }
}
