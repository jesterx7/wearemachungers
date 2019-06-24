package com.machungapp.user.wearemachungers.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bluejamesbond.text.DocumentView;
import com.bumptech.glide.Glide;
import com.machungapp.user.wearemachungers.R;

public class VisiMisiFragment extends Fragment {
    private View view;
    private DocumentView dvVisi, dvMisi;
    private ImageView imgVisiMisiCover;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.visi_misi, container, false);

        dvVisi = view.findViewById(R.id.dvVisi);
        dvMisi = view.findViewById(R.id.dvMisi);
        imgVisiMisiCover = view.findViewById(R.id.imgVisiMisiCover);

        Glide.with(view.getContext())
                .load("https://hoteldekatkampus.com/wp-content/uploads/2014/10/universitas-ma-chung.jpg")
                .crossFade()
                .into(imgVisiMisiCover);

        String misi = "&#8226; Menyelenggarakan Tri Dharma Perguruan Tinggi yaitu pendidikan dan pengajaran tinggi, penelitian, dan pengabdian kepada masyarakat secara berkualitas, fokus, dan sesuai dengan kebutuhan masyarakat kini dan akan datang.<br><br>" +
                "&#8226; Membentuk dan mengembangkan angkatan - angkatan motivator dan pemimpin masyarakat yang memiliki potensi dan kapasitas moral yang luhur, berjiwa kepemimpinan dan kewirausahaan yang betitik berat pada pembentukan akhlak dan kepribadian unggul, rendah hati, melayani, dan berkontribusi sebagai manusia yang utuh<br><br>" +
                "&#8226; Mendorong dan mengembangkan sikap serta pemikiran yang kritis-prinsipil dan kreatif-realistis berdasarkan kepekaan hati nurani yang luhur.<br><br>" +
                "&#8226; Menghasilkan lulusan siap pakai yang berkualitas tinggi yang mampu bersaing di pasar global.<br><br>" +
                "&#8226; Berperan aktif dalam meningkatkan peradaban dunia dengan menghasilkan lulusan yang berwawasan global, toleran, dan cinta damai, serta produktif dalam menghasilkan karya cipta yang mendukung peningkatan martabat manusia global<br><br>" +
                "&#8226; Melaksanakan pengelolaan perguruan tinggi berdasarkan prinsip ekonomis dan akuntabilitas.";

        dvVisi.setText("Memuliakan Tuhan melalui akhlak, pengetahuan, dan kontribusi nyata sebagai insan akademik yang berdaya cipta.\n");
        dvMisi.setText(Html.fromHtml(misi));
        return view;
    }
}
