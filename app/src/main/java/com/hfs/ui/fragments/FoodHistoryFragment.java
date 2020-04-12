package com.hfs.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hfs.lib.StandardProfile;
import com.hfs.ui.FoodHistoryAdapter;
import com.hfs.ui.HFSApplication;
import com.hfs.ui.R;
import com.hfs.ui.di.DaggerHomeComponent;
import com.hfs.ui.di.DaggerProfileComponent;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodHistoryFragment extends Fragment {

    private static final String TAG ="FoodStuff ;";
    private ArrayList<String> fAmounts = new ArrayList<>();
    private ArrayList<String> fImageUrls = new ArrayList<>();
    private ArrayList<String> fDates = new ArrayList<>();
    private View fragment;

    @Inject StandardProfile profile;


    public FoodHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_history, container, false);
        fragment = view;
        Log.d( TAG, "onCreateView: has been created");

        DaggerProfileComponent.builder()
                .appComponent(((HFSApplication) this.getActivity().getApplication()).getAppComponent())
                .build()
                .inject(this);
        Log.d(TAG, "onCreateView: "+ profile);


        initImageBitmaps();
        initFoodRecyclerView();

        AddFoodItemFragment frag = new AddFoodItemFragment();
        FloatingActionButton addnew = (FloatingActionButton) view.findViewById(R.id.addNewFoodBtn);
        addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFoodItemFragment frag = new AddFoodItemFragment();

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, frag);
                fragmentTransaction.commit();
            }
        });

        return view;
    }


    private void initImageBitmaps(){

        fImageUrls.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSWApmNDcMVLGFeZSnzoKVDiVAm0qcOFBJz3bV95s8680am11NC&usqp=CAU");
        fAmounts.add("36g");
        fDates.add(LocalDate.now().toString());

        fImageUrls.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTTq4QwWTSKzXW9gGYdjOQczvcgFrw0UCplJs6m9qQh_mYNjf92&usqp=CAU");
        fAmounts.add("50g");
        fDates.add(LocalDate.now().toString());

        fImageUrls.add("data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxMTEhUSEhIVExUWGBYXFxcTGBIVFhUVFRUXFxUVFRUZHykgGBomHRYVIjEhJSk3Li4uFx8zODMsNyguLisBCgoKDg0OGxAQGy0lICUvLS0tLS81LS8tLTUtNy0uLS8tLTU1LS0tKy0tLS0rLS0tLS0tLS0tNS0tLS0tKy4rLf/AABEIAMsA+AMBIgACEQEDEQH/xAAbAAEAAQUBAAAAAAAAAAAAAAAABgECBAUHA//EAEIQAAEDAgQDBQUFBQUJAAAAAAEAAgMEEQUSIUEGMVEHEyJhcSMyUoGRFDNCYqEVgpKxwUNTcsLwFhckc4OistHS/8QAGQEBAAMBAQAAAAAAAAAAAAAAAAECAwQF/8QAJxEBAAIBAwMDBAMAAAAAAAAAAAECAxEhQRIxYQQTUSIygfBCkaH/2gAMAwEAAhEDEQA/AO4oiICpmF7bqjnK1h1QeiIiAiIgIiICK0lU163QXoqAqqAiIgIiICIiAqNdfUKxzlezkgqiIgIiICIhKAisJ87Kt7c0FyIiAqOKFebQgAXXoAgCqgIiICIiAiIgsaq31QjcIAgNG6uREBERAREQF5uKSFVa3qgMbur0RAREQEREBWu2VyoQgpfqqJqrgEAKqIgIiICIiAiIgIiICIiAiIgIiICIiAiIgEIiICIiAiIgIiICIiAiIgIiICIiAiIgIiICIiAiIgIiICIqONtToEFUUPxvtLw6nuDP3zh+GnHeajbOPAD5Fyh1f24C/sKIkdZpQ0/wsa7+arNohaKWl2FFw2Ttrq+YpoLeZlP9Qvan7b5gfaUUbh+SV7D+rXKOuqfbs7Yi5zhHbHQSkCZstMer252fxR3PzICnuH4hFOwSQSslYeTo3Bw+o3VomJVmJjuyURFKBERAREQEREBERAREQEREBERAREQFS6gfa9j08EENPSktmq5O6a5pyuaNAQ134XEuYM2wJPOxUdxns2bQ0T6yGsnZWQMMr5Wus2VzfE5mXnY8hcm+9+SrMrRXZ19FpuDcVdVUVPUPy55I2l+XlntZ2m2u2y3KsrIiLnPEfFM9ZK+iwx4Y1hy1NbzbF1jht70nmPlb3hFrRWNZWrWbTpDZ8Y9ocFG7uImmqqjoIYrnK61x3hANj+UAu8raqD1nDuM4oc1bM2lhPKEXsB5wsPi/6jrjyUr4cwOmoWkQR5pD780vilkJ5knYX2Gnz1W1fUuO5+Wi4snqeIdmP00ofSdkVI23ezzPts0xxt+lif1WxZ2Z4WOcTj6zzf0cFuiiw9+W/seWo/3a4XtAflPUf/axqnsmw93u9/H/AIZM3/mHKQLzpKxrwTFIHAEtJY4EBzTZzTbcHZT70onB5QXEexs86erv+WZn+dh/yqMP4dxbDH99E2RluclMe8Y4D+8aObf8bbLtrK1w8/VZUVa08/D/AC+qvXMzthtCF8EdrsU+WGuywyGwErdIXn81/uz6+HzHJdRBvqFAeJ+AaOsBdkEMp172IAEnq9vKT56+YUUwrF8QwJwiq2mpobgNey57sE2GQn3P+W7T4T166ZYnu5b4vh2pFhYPi0NVE2enkEkbuRbsd2uB1a4bg6hZq2YiIiAiIgIite63mguREQFa4q5W7oKZR0VRohugCC5UJVVz7txr5YsNyxkgSysikLeYjLXuI9CWtb5h1t1EzpCYjWdHOKub9qYrPHUVxgAfK2ldcujEjJQyBrLGzcwBdmBBJAsbkLrGNVDRh8tLJkxSoihZ38DXtZLIPCS8tbdzDbxCwubC2pWNhOC4dFDHPDBT5GMztmDIy/KG3MneWuTa5vdQbDseocQq46nvHYdWRvbZ2ZpbPGCAGPcbDMW+Gx9PEBpM06ed5RGTqnaNoYk9NBT0n7TwjEJaUZ8j6aZ1yZQATEBqHuA1s4OBGuYBSrgTtZZK10eIujhe0AtlF2skHIgt1yuGm9jfayiXFuLxVWMFtU9rKenJY0W8DnixeXkaDM/mTpaMBbXiHBaEQvnfTsOVuYd2THnJ0YAWEXuSB81y3zdF9Hdi9P7tOrVvOMOMftrmYfhkwd3oJqKhl7RQ3s4MPxHlcdQOZu3aYVhsVLC2KJoZGwfy1c5x+Le6inZphzKajNTKQ0y3e5xtZsLL5Nelru/eC10wrMakdHT+xomus6RwOVxHUXvId8o0G5GiyvM5LeIXpEY6+ZbfGO0ejhJDC6cj+6sGfxnT+G6w6Xi/EZxenwtxGznGTKQeVnFrGn5FS/h7giiogHCMSSD+1ms59/yDkz90fVb51e3oSqTFITFsluzm8mI48Nf2dHboC1x/Sa/6LGZ2hSwvDK+ikgvpmAeNfJjwMw8w4rqIr29D+iTshnYY5GskY7QskAIPyPNR9E8EzkhocJxeGpZngkbIN7e809HNOrT6hZMEDGCzGNYCS4hoDQXON3Gw3J1JUJ4o7Opad/2vCnva5tyYQTmA37on3x+R3yvoFn8D8XCsaYpQGVDB4hyDwDYuaDyIOhbtf6Uvj0jWOzWmWLbT3StVa3oqBXRvss4aSvimc0/0WayZkgLHAG4sWuAIcDzFjzHktPh9S+RneSwuhcSQI3OaXZRycS3QE9NvPmvZX6prszmkX3RLGMAqcLlNbhVzEdZ6TVzCBzcxvMj01btcaKbcF8b02IsvE7JKBd8LiM7fNvxs/MPnY6K+mrbaO+v/ALUQ4u7PWyv+10D/ALNUg5/CS1kjviBb928/ENDc3Gt114s7ky4fl1NFyDAe1Wamf9lxeB7XtsDK1oDrbOfGNHDT3maHYLqOD4xBVR97TzMlZ1Yb2PRw5tPkdV1xaJctqzHdnIitc6ylUc5Wc0svQBBVERAVCFVEFoarkRAXI+IuIa/EqmqoKGCCSCC7Je/AOdzXEHVxs052uDba+C9xt1tzrC52XEuAK2tdX1FbQ0TnUdVNaUPfG3Ld2YyBzjfMM73WAI8WXYEVt8L05louAcCZUSTUNXWVFHkvmpw/u2SWNpGkOOXMDbTKbg3HJdSx3CMEdBHTVElLG2NoZE4zRRysaPhkLsx11N7gnndZfFfZ1RV8ommD2SWAc6JwbnA93OCCCRyva9rDYW5p2ldn1JQQwup3TGSWZsQEjmFti1xJ0YDe4bvuVXesLaxae7Yy9ihc8GCuYYXWIc+PM8NNiC0tcGvv1FuaiHGOGVGHOdh75RJC7LLG7y10LLksF73byNrjmVLm8NYt3bKf9qFkLGhjRFmYWsaAA0Fga46C2rlm4P2fUcTu8mD6uS9yZj4L9TGL5v3iVz2y43TTFlhg4bRTYuI2ZHU2HR5b2NnVBYAAxn5BbmNL9T7vR4zHAxsUTGtawZWtbo1oGyw6qvDG3e5sbBZupDGi5DWi58yAB5rxqqhsbHSPcGsYC5xPINAuSVzWycVb1xc2l7ySFxuStPjGPMp5IYSySSSZ1mtibmIaCA57ujRmH+gthR1LZY2SNvle1rm5gWmzhcXB1B1Xssud23GwiLW01dM6pkiNOWQsaMsznD2jza4az4bE632URCdW+pKo+675Fc47R6EUWIUuIRDKJH5ZgNASLBzrdXMc6/mwHdTtQbtqqrxUcQ99z3v9MrWs+pL/ANCt8Vtdpc+avTMTCcrwjrY3PdE2RrpGAFzA4Zmh3LMOYuvYBY8FDFG58jI2MdIbyOAALyN3O3WMOiWSSi8KKsjlbnie2RhJGZhDmktNjqPML3UELO9bmyZhmtmy3GbLe17c7X3WRDOW8uXTZa6PC4mzvqQz2r2tY55JJyt5NAJs0abc91SsxJsckURbI50pIGRpc1oaLl0juTW6j6qedkTppuzcYwqlrmd3Uxh1vdPJ7T1Y8aj033uoPXdn8+H5q3Dat4dE0vMcgHjY3VzS5tmvFr+FzfmDqpwo5x7jz2U/2KEGSpqvZRtb7wY7R58tLgHa9+QK3xZJmdHPlxREawnXB+Oiuo4aoNymQHM3Zr2uLHgHcZmm3lZbUtN1q+EMEFFRw0oIJjb4iOTnuJdIR5ZnOstwvSh5s99lAFVEUoEREBERAREQFw7hzhKKqqq9r5ZoI4Kt4bBC/uwBncWvd0FgMpFrAGxXcVGeIeBaOre6SRr45HtyvfA90Zkbp4ZANHiwA1GyjTdOu0whTO1SodJIKfD5qiJj3MEkRkfcDkSBE4C4sbX5ELVccYxVYnDHG3DK+N8cgka4wyEe65pFw0W94G/kuvcP4FBRwiCnZkYCTqS4ucebnOOpOg+QA5BbJJ1mNyOmJ1iHBKHDOI5XAATRj4pTAxo9QRc/Qq7EcXxug1q4BJGOchY1zLeckJAZ+8F3lUIvodVlOCk8No9ReHBpeN6SuhdTVkb4A+3jYe8a1wN2uuBfQgGxaR1WkxP7TCyJlRI6sw9rgWuheO7kA91j5ACRY28DjbYHkRJO2DgNsH/HUseWIn20bB4Y3HlI0DkwnQgaA2O5tCOH4atrXTUb7kfeRsOZ1ti+FwtI0+V91hbHFHRXJORLMI42nJLhapmnIbDSRjIyBrSdXykakj1GlyQNBIpeMWid7crRTwNP2ioLjlZLbSKOw9o69hYa8+mvNnVtHIctTTPpZd30ugv1dTP0b+6brLp6Z/shT4hTVEcTs8cNQTFZ23sZdDa/xabLOaVnhpF7Q63FikZZE9x7vvsvdtls15LhcNyc81ttlmrllPDipqHVRZTukLcjZJJInMhbv3QD/DffQ79TeR4dj7KSJ326vjqJnOzZYsrsgsAGNawDe+pAGqynH8Na5PlLpZA1pc4hoAJJJsABqSTsuW0k5xXFmygHuIMpF7i0cbi5l/N79bdL9F719XW4ue7gjdT0l9XyXGex5uP4rfA3S41PSc8O4FFRwiKIX3e8+893xH+g2U/ZHlE/XPhtF5zwte1zHtDmuBa5p1BaRYgjpZeixp66Nr2RGRoklDu7afxZG5nEDcAarGGq6io44mCOJjY2Dk1gAAubnQea9nOAtcgX0F9z0CiWDcSv/Z81RM5sksJnBLW5WPMZOTKB+E+EX581qcVxZ3e4XJVvbHlbLNJbRveCMZNNddbfMgc1fonXdXq22Sur4hja+piaCZKeEzOuPAfCXBt73vy+vqohU8ZSMkZNJJkZJh7ZGxNuWmpke7KR05czsFrZsSmnmrJqYNjgqBGx9RPdjGsYwsdlvzJudNToNBtrsHjzSthw+J1XUEAd9I3wxgWGaON2jGj43nTbZbUxx20ZXvPfX9/fw9KKtqIY6cSvluwg09LEXCSV7nlwknA8RaXGwadXbAak9e7PeDnwF1bWkSVso8iIGH+zZtfqR6C+pddwHwAyjJqKh32isfq6Q3cI7jVsZOt9i86kdBopuuzHj03nu4cubq+mOwiItmAiIgIiICIiAiIgIiICIiAiIgsljDmlrgHNcCCHAEEEWIIPMLj3FnZhPTS/a8KJsLkwX8beojJ0ez8h16X0A7IirasWjSVq2ms6w4Rh3ElNVewromxTA5S2Ztm5ugLtY3cvCdehK9a3s+pX6sMkXk1wc36PBP6rrHEXClHWi1TA15tYPF2yN9JG2dbyvZQ2bs4qqcH9n15y7Q1bQ9o8hI0XaPRq5LemtH2S9CnrKWjTLH5RCPs1pwbmaQ+gjB+tisuSgw3DsjpI23cSGuka+Y3aBc21DeY5DdZ87cYg++w5s4+KmeD9GauP0CwZ+J+QqMNrG2P4oM1j5ZrLGaZf5auiL+n0+nTXyti48aZaiTvm9zHG0Qx2DXTSakubcX52b6a2WI7HKl3d08tayNzrzzyh0TO6jJ8NPCd3aG/TzHPDr8Sw6W+bD6jMfgjERv6seFrxS07/ALvCq1521nt5XLWn6q1aeJZ2v5j/AFny8TF9NVzPqvaVHs4oA43ijzFuYsHunK4knew62WN/tBTQ1EBiD52wU4hiABZeUkhzvEL+70G6yqHhqqf91gRF96iSW30lc1STDuAMWdydR0DTz7hgdJ6GzTf+NXjFM8M5zVjmESpKWtNGIJDDSU+uZ8/ge8OcXHQ3PM9B6rGi+zCQNYJsUqOTWkSd2LaaM1e8Dp7voupYf2P0t89ZPPWP3zOMbfllOf8A71OsJwenpm5KeGOFu4jaG383EauPmVrXBPLG3qY4j+3JsL7Nq6uc2TEZRTxC2WCLKXAdA0XZH6nMdtF1TAMAp6OPuqaJsbd7auefie46uPmVs0W9aRXs5r3tadZERFZQREQEREBERAREKAvMG58lRzlc1u6C9ERAREQEREFCVTOFTmq+qC5Fa1XICIiAiIgIitc5Ac5GclYBdegCCqIiAiIgIitcUAuVQVQBUJ+qC9FQKqAvNzl6FWtYgo1vVXoiAiIgIiICIiCwdCiuIQBAAVURAREQEREFrnKwBXubdVAQAFVEQEREBERAVrgrkQWkoAq5VVAREQEREBERAREQEREBERAREQEREBERAREQEREBERAREQEREBERAREQEREBERAREQEREBERAREQEREBERAREQEREBERAREQEREBERAREQEREBERB//Z");
        fAmounts.add("30g");
        fDates.add(LocalDate.now().toString());

        fImageUrls.add("https://cdn5.vectorstock.com/i/1000x1000/84/44/doodle-broccoli-vector-12608444.jpg");
        fAmounts.add("20g");
        fDates.add(LocalDate.now().toString());
    }
    private void initFoodRecyclerView(){
        RecyclerView recyclerview = fragment.findViewById(R.id.foodRecView);
        FoodHistoryAdapter adapter = new FoodHistoryAdapter(this.getContext(), fAmounts,fImageUrls,fDates);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this.getContext()));

    }
}
