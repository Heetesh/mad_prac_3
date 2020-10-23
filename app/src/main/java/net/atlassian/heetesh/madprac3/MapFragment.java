package net.atlassian.heetesh.madprac3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MapFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map, container, false);

        /*TODO: Add recycler view for map*/

        // The Recycler View
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv_map);

        // Setting the recycler view layout
        rv.setLayoutManager(new GridLayoutManager(
                getActivity(),
                MapData.HEIGHT,
                GridLayoutManager.HORIZONTAL,
                false
        ));

        // Getting the data
        MapData mapData = MapData.get();

        // Map Adapter class to handle data
        MapAdapter mapAdapter = new MapAdapter(mapData);

        // Hooking up recycler view using the adapter
        rv.setAdapter(mapAdapter);


        return view;
    }

    private class MapAdapter extends RecyclerView.Adapter<MapDataVHolder> {
        private MapData mapData;

        public MapAdapter(MapData mapData) {
            this.mapData = mapData;
        }

        @NonNull
        @Override
        public MapDataVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater li = LayoutInflater.from(
                    getActivity()
            );
            return new MapDataVHolder(li, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull MapDataVHolder holder, int position) {
            // Using a column-major order mapping
            int row = position % MapData.HEIGHT;
            int col = position / MapData.HEIGHT;

            holder.bind(mapData.get(row, col)); // gets the position mapElement
        }

        @Override
        public int getItemCount() {
            return MapData.HEIGHT * MapData.WIDTH;
        }
    } // end of MapAdapter class

    private class MapDataVHolder extends RecyclerView.ViewHolder {
        private StructureData data = StructureData.get();
        private ImageView topLeft, topRight, bottomLeft, bottomRight;
        private ImageView mainStruct;


        public MapDataVHolder(LayoutInflater li, ViewGroup parent) {
            super(li.inflate(R.layout.grid_cell, parent, false));

            int size = parent.getMeasuredHeight() / MapData.HEIGHT + 1;
            ViewGroup.LayoutParams lp = itemView.getLayoutParams();

            lp.width = size;
            lp.height = size;

            topLeft = itemView.findViewById(R.id.i_view_top_left);
            topRight = itemView.findViewById(R.id.i_view_top_right);
            bottomLeft = itemView.findViewById(R.id.i_view_bottom_left);
            bottomRight = itemView.findViewById(R.id.i_view_bottom_right);

        }

        public void bind(MapElement mapElement) {
            topLeft.setImageResource(mapElement.getNorthWest());
            topRight.setImageResource(mapElement.getNorthEast());
            bottomLeft.setImageResource(mapElement.getSouthWest());
            bottomRight.setImageResource(mapElement.getSouthEast());

            if (mapElement.getStructure() != null) {
                mainStruct.setImageResource(mapElement.getStructure().getDrawableId());
            }
        }
    }
}
