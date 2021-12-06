package ca.mcgill.ecse321.library;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import ca.mcgill.ecse321.library.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        final View routeListingsView = inflater.inflate(R.layout.fragment_first, null);

        initializeProfileInfo(routeListingsView);

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return routeListingsView;
        //return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initializeProfileInfo(View route){
        System.out.println("test");
        TextView emailView = (TextView) route.findViewById(R.id.email);
        emailView.setText("RIP");

        TextView passwordView = (TextView) route.findViewById(R.id.password);
        emailView.setText("NEW TEXT");

        TextView numberView = (TextView) route.findViewById(R.id.street_number);
        emailView.setText("NEW TEXT");

        TextView nameView = (TextView) route.findViewById(R.id.street_name);
        emailView.setText("NEW TEXT");

        TextView cityView = (TextView) route.findViewById(R.id.city);
        emailView.setText("NEW TEXT");

        TextView countryView = (TextView) route.findViewById(R.id.country);
        emailView.setText("NEW TEXT");
    }
}