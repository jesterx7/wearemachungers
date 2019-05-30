package com.example.user.wearemachungers.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.wearemachungers.Classes.Newsletter;
import com.example.user.wearemachungers.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ListNewsletterAdapter extends RecyclerView.Adapter<ListNewsletterAdapter.NewsletterViewHolder> {
    private Context context;
    private ArrayList<Newsletter> listNewsletter;

    public ListNewsletterAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Newsletter> getListNewsletter() {
        return listNewsletter;
    }

    public void setListNewsletter(ArrayList<Newsletter> listNewsletter) {
        this.listNewsletter = listNewsletter;
    }

    @Override
    public NewsletterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_newsletter, parent, false);
        return new NewsletterViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(NewsletterViewHolder holder, final int position) {
        holder.tvNamaPDF.setText(getListNewsletter().get(position).getNamaPDF());
        holder.imgDownloadPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try  {
                            URL url = new URL(getListNewsletter().get(position).getUrl());
                            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                            httpURLConnection.setRequestMethod("GET");
                            httpURLConnection.setDoOutput(true);
                            httpURLConnection.connect();
                            FileOutputStream fileOutputStream = new FileOutputStream(new File(context.getExternalFilesDir(null),
                                    "pdf_" + String.valueOf(position) + ".pdf"));
                            InputStream inputStream = httpURLConnection.getInputStream();
                            byte[] buffer = new byte[1024];
                            int length;
                            while ((length = inputStream.read(buffer)) > 0) {
                                fileOutputStream.write(buffer, 0, length);
                            }
                            fileOutputStream.close();
                            Handler handler = new Handler(Looper.getMainLooper());
                            handler.post(new Runnable() {
                                public void run() {
                                    Toast.makeText(context, "Downloaded" + "pdf_" + String.valueOf(position) + ".pdf" , Toast.LENGTH_SHORT).show();
                                }
                            });
                            File file = new File(context.getExternalFilesDir(null), "pdf_" + String.valueOf(position) + ".pdf");
                            Uri path = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file);
                            if (Build.VERSION.SDK_INT >= 24) {
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setDataAndType(path, "application/pdf");
                                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                PackageManager pm = context.getPackageManager();
                                if (intent.resolveActivity(pm) != null) {
                                    context.startActivity(intent);
                                }
                            } else {
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setDataAndType(Uri.parse("file://" + path), "application/pdf").setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListNewsletter().size();
    }

    public class NewsletterViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNamaPDF;
        private ImageView imgDownloadPDF;

        public NewsletterViewHolder(View itemView) {
            super(itemView);

            tvNamaPDF = itemView.findViewById(R.id.tvNamaPDF);
            imgDownloadPDF = itemView.findViewById(R.id.imgDownloadPDF);
        }
    }
}
