package com.example.apptravels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.apptravels.banco.model.ViagemModel;

import java.util.List;

public class ViagemAdapter extends ArrayAdapter<ViagemModel> {

    public ViagemAdapter(Context context, List<ViagemModel> viagens) {
        super(context, 0, viagens);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViagemModel viagem = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_viagem, parent, false);
        }

        TextView textViewDestino = convertView.findViewById(R.id.textViewDestino);
        TextView textViewDataViagem = convertView.findViewById(R.id.textViewDataViagem);
        TextView textViewNumPessoas = convertView.findViewById(R.id.textViewNumPessoas);

        textViewDestino.setText("Destino: " + viagem.getDestino());
        textViewDataViagem.setText("Data da Viagem: " + viagem.getDataViagem());
        textViewNumPessoas.setText("Número de Pessoas: " + viagem.getNumPessoas());

        return convertView;
    }
}
