package com.example.user.wearemachungers;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluejamesbond.text.DocumentView;
import com.bumptech.glide.Glide;

public class InformasiFragment extends Fragment {
    private View view;
    private ImageView imgCoverInformasi;
    private DocumentView tvVisiInformasi, tvMisiInformasi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.informasi, container, false);
        imgCoverInformasi = view.findViewById(R.id.imgCoverInformasi);
        tvVisiInformasi = view.findViewById(R.id.tvVisiInformasi);
        tvMisiInformasi = view.findViewById(R.id.tvMisiInformasi);

        Glide.with(view.getContext())
                .load("https://hoteldekatkampus.com/wp-content/uploads/2014/10/universitas-ma-chung.jpg")
                .crossFade()
                .into(imgCoverInformasi);

        tvVisiInformasi.setText("Memuliakan Tuhan melalui akhlak, pengetahuan, dan kontribusi nyata sebagai insan akademik yang berdaya cipta.");
        tvMisiInformasi.setText("Menyelenggarakan Tri Dharma Perguruan Tinggi yaitu pendidikan dan pengajaran tinggi, penelitian, dan pengabdian kepada masyarakat secara berkualitas, fokus, dan sesuai dengan kebutuhan masyarakat kini dan akan datang.\n\n" +
                "Membentuk dan mengembangkan angkatan-angkatan motivator dan pemimpin masyarakat yang memiliki potensi dan kapasitas moral yang luhur, berjiwa kepemimpinan dan kewirausahaan yang betitik berat pada pembentukan akhlak dan kepribadian unggul, rendah hati, melayani, dan berkontribusi sebagai manusia yang utuh\n\n" +
                "Mendorong dan mengembangkan sikap serta pemikiran yang kritis-prinsipil dan kreatif-realistis berdasarkan kepekaan hati nurani yang luhur.\n\n" +
                "Menghasilkan lulusan siap pakai yang berkualitas tinggi yang mampu bersaing di pasar global.\n\n" +
                "Berperan aktif dalam meningkatkan peradaban dunia dengan menghasilkan lulusan yang berwawasan global, toleran, dan cinta damai, serta produktif dalam menghasilkan karya cipta yang mendukung peningkatan martabat manusia global\n\n" +
                "Melaksanakan pengelolaan perguruan tinggi berdasarkan prinsip ekonomis dan akuntabilitas.");

        return view;
    }
}
