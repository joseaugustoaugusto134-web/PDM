package br.edu.ifsul.pdm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorDoRecyclerView extends RecyclerView.Adapter<ModeloDeLinha> {
    private final List<String> dados;
    private Context ativityEmExecucao;

    public AdaptadorDoRecyclerView() {
        dados = new ArrayList<>();
    }
    public List<String> getDados(){ return dados;}

    @Override
    public ModeloDeLinha onCreateViewHolder(ViewGroup parent, int viewType) {
        ativityEmExecucao = parent.getContext();
        ModeloDeLinha holder=new ModeloDeLinha(LayoutInflater.from(ativityEmExecucao)
                .inflate(R.layout.modelo_de_linha, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ModeloDeLinha linhaExibida, int position) {
        String dadoDaLinha = dados.get(position);
        linhaExibida.title.setText(dadoDaLinha.toString());
    }

    @Override
    public int getItemCount() {
        return dados!= null ? dados.size() : 0;
    }
}

