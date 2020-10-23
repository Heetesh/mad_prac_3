package net.atlassian.heetesh.madprac3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SelectorFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selector, container, false);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv_selector);

        rv.setLayoutManager(new LinearLayoutManager(
                getActivity(),
                RecyclerView.HORIZONTAL,
                false
        ));

        // Getting the data
        StructureData structureData = StructureData.get();

        // The structure adapter
        StructureAdapter structureAdapter = new StructureAdapter(structureData);

        // Hooking up the recycler using the adapter
        rv.setAdapter(structureAdapter);

        return view;
    }

    private class StructureAdapter extends RecyclerView.Adapter<StructureDataVHolder> {
        private StructureData structureData;

        public StructureAdapter(StructureData structureData) {
            this.structureData = structureData;
        }

        @NonNull
        @Override
        public StructureDataVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater li = LayoutInflater.from(
                    getActivity()
            );

            return new StructureDataVHolder(li, parent);

        }

        @Override
        public void onBindViewHolder(@NonNull StructureDataVHolder holder, int position) {
            holder.bind(structureData.get(position));
        }

        @Override
        public int getItemCount() {
            return structureData.size();
        }
    } // End of StructureAdapter

    private class StructureDataVHolder extends RecyclerView.ViewHolder {

        ImageView imgStruct;
        TextView structInfo;

        public StructureDataVHolder(LayoutInflater li, ViewGroup parent) {
            super(li.inflate(
                    R.layout.list_selection, parent, false));

            imgStruct = itemView.findViewById(R.id.i_view_list_selection); // gets the img view
            structInfo = itemView.findViewById(R.id.tv_list_selection); // gets info text for struct

        }

        public void bind(Structure structure){
            imgStruct.setImageResource(structure.getDrawableId());
            structInfo.setText(structure.getLabel());

            imgStruct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
