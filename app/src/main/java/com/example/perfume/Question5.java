package com.example.perfume;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.perfume.adapter.FlavorAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Question5#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Question5 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static String q5_flavor;
    Context context;
    View v;

    RecyclerView add_flavor_grid;
    FlavorAdapter flavorAdapter;

    ArrayList<Integer> drawables_Num;

    ImageView q5_frame4;

    public Boolean q5_state = false;
    public String q5_result;
    public Integer q5_position;


    public Question5(String flavor) {
        q5_flavor = flavor;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Question5.
     */
    // TODO: Rename and change types and number of parameters
    public static Question5 newInstance(String param1, String param2) {
        Question5 fragment = new Question5(q5_flavor);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = container.getContext();
        v = inflater.inflate(R.layout.fragment_question5, container, false);
        q5_frame4 = (ImageView)v.findViewById(R.id.q5_frame4);

        // 고른 향료에 따라 이미지 다르게 뿌려주기
        if(q5_flavor.equals("1"))
            flavor_aldehyde(v);
        if(q5_flavor.equals("2"))
            flavor_animalic(v);
        if(q5_flavor.equals("3"))
            flavor_aromatic(v);
        if(q5_flavor.equals("4"))
            flavor_balsam(v);
        if(q5_flavor.equals("5"))
            flavor_chypre(v);
        if(q5_flavor.equals("6"))
            flavor_citrus(v);
        if(q5_flavor.equals("7"))
            flavor_green(v);
        if(q5_flavor.equals("8"))
            flavor_floral(v);
        if(q5_flavor.equals("9"))
            flavor_fruity(v);
        if(q5_flavor.equals("10"))
            flavor_spicy(v);
        if(q5_flavor.equals("11"))
            flavor_woody(v);
        if(q5_flavor.equals("12"))
            flavor_aquatic(v);
        if(q5_flavor.equals("13"))
            flavor_nutty(v);
        if(q5_flavor.equals("14"))
            flavor_leather(v);

        return v;
    }

    // 시트러스
    private void flavor_citrus(View v) {
        q5_frame4.setImageResource(R.mipmap.question_5_text_citrus);
        drawables_Num = new ArrayList<>();

        drawables_Num.add(7);
        drawables_Num.add(9);
        drawables_Num.add(8);
        drawables_Num.add(1);
        drawables_Num.add(11);
        drawables_Num.add(2);
        drawables_Num.add(10);
        drawables_Num.add(3);
        drawables_Num.add(5);
        drawables_Num.add(12);

        add_flavor_grid = (RecyclerView)v.findViewById(R.id.add_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        add_flavor_grid.setLayoutManager(mLayoutManager);
        add_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(v.getContext(), drawables_Num);
        add_flavor_grid.setAdapter(flavorAdapter);

        add_flavor_grid.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                // RecyclerView로 전달된 TouchEvent를 받는다.
                if (e.getAction() == MotionEvent.ACTION_DOWN) {
                    View reV = rv.findChildViewUnder(e.getX(), e.getY());
                    int position = rv.getChildAdapterPosition(reV);
                    if (position >= 0) {
                        String num = String.valueOf(flavorAdapter.getNum(position));
                        if (q5_state == false) {
                            flavorAdapter.setDrawable(position);
                            q5_result = num;
                            q5_position = position;
                            q5_state = true;
                        } else if (q5_state == true && (!num.equals(q5_result))) {
                            flavorAdapter.setBackDrawable(q5_position);
                            flavorAdapter.setDrawable(position);
                            q5_result = num;
                            q5_position = position;
                        }
                        ((QuestionActivity) QuestionActivity.context).nextPage(4, q5_state, q5_result);
                    }
                }
                return false;
            }
            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            }
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });

        flavorAdapter.notifyDataSetChanged();
    }

    // 알데하이드
    private void flavor_aldehyde(View v) {
        q5_frame4.setImageResource(R.mipmap.question_5_text_aldehyde);
        drawables_Num = new ArrayList<>();

        drawables_Num.add(8);
        drawables_Num.add(11);
        drawables_Num.add(2);
        drawables_Num.add(4);

        add_flavor_grid = (RecyclerView)v.findViewById(R.id.add_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        add_flavor_grid.setLayoutManager(mLayoutManager);
        add_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(v.getContext(), drawables_Num);
        add_flavor_grid.setAdapter(flavorAdapter);

        add_flavor_grid.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                // RecyclerView로 전달된 TouchEvent를 받는다.
                if (e.getAction() == MotionEvent.ACTION_DOWN) {
                    View reV = rv.findChildViewUnder(e.getX(), e.getY());
                    int position = rv.getChildAdapterPosition(reV);
                    if (position >= 0) {
                        String num = String.valueOf(flavorAdapter.getNum(position));
                        if (q5_state == false) {
                            flavorAdapter.setDrawable(position);
                            q5_result = num;
                            q5_position = position;
                            q5_state = true;
                        } else if (q5_state == true && (!num.equals(q5_result))) {
                            flavorAdapter.setBackDrawable(q5_position);
                            flavorAdapter.setDrawable(position);
                            q5_result = num;
                            q5_position = position;
                        }
                        ((QuestionActivity) QuestionActivity.context).nextPage(4, q5_state, q5_result);
                    }
                }
                return false;
            }
            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            }
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });

        flavorAdapter.notifyDataSetChanged();
    }

    // 애니멀
    private void flavor_animalic(View v) {
        q5_frame4.setImageResource(R.mipmap.question_5_text_animalic);
        drawables_Num = new ArrayList<>();

        drawables_Num.add(9);
        drawables_Num.add(8);
        drawables_Num.add(11);
        drawables_Num.add(4);
        drawables_Num.add(13);
        drawables_Num.add(10);
        drawables_Num.add(14);
        drawables_Num.add(3);
        drawables_Num.add(12);

        add_flavor_grid = (RecyclerView)v.findViewById(R.id.add_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        add_flavor_grid.setLayoutManager(mLayoutManager);
        add_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(v.getContext(), drawables_Num);
        add_flavor_grid.setAdapter(flavorAdapter);

        add_flavor_grid.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                // RecyclerView로 전달된 TouchEvent를 받는다.
                if (e.getAction() == MotionEvent.ACTION_DOWN) {
                    View reV = rv.findChildViewUnder(e.getX(), e.getY());
                    int position = rv.getChildAdapterPosition(reV);
                    if (position >= 0) {
                        String num = String.valueOf(flavorAdapter.getNum(position));
                        if (q5_state == false) {
                            flavorAdapter.setDrawable(position);
                            q5_result = num;
                            q5_position = position;
                            q5_state = true;
                        } else if (q5_state == true && (!num.equals(q5_result))) {
                            flavorAdapter.setBackDrawable(q5_position);
                            flavorAdapter.setDrawable(position);
                            q5_result = num;
                            q5_position = position;
                        }
                        ((QuestionActivity) QuestionActivity.context).nextPage(4, q5_state, q5_result);
                    }
                }
                return false;
            }
            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            }
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });

        flavorAdapter.notifyDataSetChanged();
    }

    // 프루티
    private void flavor_fruity(View v) {
        q5_frame4.setImageResource(R.mipmap.question_5_text_fruity);
        drawables_Num = new ArrayList<>();

        drawables_Num.add(7);
        drawables_Num.add(6);
        drawables_Num.add(8);
        drawables_Num.add(11);
        drawables_Num.add(2);
        drawables_Num.add(13);
        drawables_Num.add(10);
        drawables_Num.add(3);
        drawables_Num.add(12);

        add_flavor_grid = (RecyclerView)v.findViewById(R.id.add_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        add_flavor_grid.setLayoutManager(mLayoutManager);
        add_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(v.getContext(), drawables_Num);
        add_flavor_grid.setAdapter(flavorAdapter);

        add_flavor_grid.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                // RecyclerView로 전달된 TouchEvent를 받는다.
                if (e.getAction() == MotionEvent.ACTION_DOWN) {
                    View reV = rv.findChildViewUnder(e.getX(), e.getY());
                    int position = rv.getChildAdapterPosition(reV);
                    if (position >= 0) {
                        String num = String.valueOf(flavorAdapter.getNum(position));
                        if (q5_state == false) {
                            flavorAdapter.setDrawable(position);
                            q5_result = num;
                            q5_position = position;
                            q5_state = true;
                        } else if (q5_state == true && (!num.equals(q5_result))) {
                            flavorAdapter.setBackDrawable(q5_position);
                            flavorAdapter.setDrawable(position);
                            q5_result = num;
                            q5_position = position;
                        }
                        ((QuestionActivity) QuestionActivity.context).nextPage(4, q5_state, q5_result);
                    }
                }
                return false;
            }
            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            }
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });

        flavorAdapter.notifyDataSetChanged();
    }

    // 플로럴
    private void flavor_floral(View v) {
        q5_frame4.setImageResource(R.mipmap.question_5_text_floral);
        drawables_Num = new ArrayList<>();

        drawables_Num.add(7);
        drawables_Num.add(6);
        drawables_Num.add(9);
        drawables_Num.add(1);
        drawables_Num.add(11);
        drawables_Num.add(2);
        drawables_Num.add(4);
        drawables_Num.add(13);
        drawables_Num.add(10);
        drawables_Num.add(3);
        drawables_Num.add(5);
        drawables_Num.add(12);

        add_flavor_grid = (RecyclerView)v.findViewById(R.id.add_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        add_flavor_grid.setLayoutManager(mLayoutManager);
        add_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(v.getContext(), drawables_Num);
        add_flavor_grid.setAdapter(flavorAdapter);

        add_flavor_grid.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                // RecyclerView로 전달된 TouchEvent를 받는다.
                if (e.getAction() == MotionEvent.ACTION_DOWN) {
                    View reV = rv.findChildViewUnder(e.getX(), e.getY());
                    int position = rv.getChildAdapterPosition(reV);
                    if (position >= 0) {
                        String num = String.valueOf(flavorAdapter.getNum(position));
                        if (q5_state == false) {
                            flavorAdapter.setDrawable(position);
                            q5_result = num;
                            q5_position = position;
                            q5_state = true;
                        } else if (q5_state == true && (!num.equals(q5_result))) {
                            flavorAdapter.setBackDrawable(q5_position);
                            flavorAdapter.setDrawable(position);
                            q5_result = num;
                            q5_position = position;
                        }
                        ((QuestionActivity) QuestionActivity.context).nextPage(4, q5_state, q5_result);
                    }
                }
                return false;
            }
            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            }
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });

        flavorAdapter.notifyDataSetChanged();
    }

    // 아로마틱
    private void flavor_aromatic(View v) {
        q5_frame4.setImageResource(R.mipmap.question_5_text_aromatic);
        drawables_Num = new ArrayList<>();

        drawables_Num.add(7);
        drawables_Num.add(6);
        drawables_Num.add(9);
        drawables_Num.add(11);
        drawables_Num.add(2);
        drawables_Num.add(4);
        drawables_Num.add(10);
        drawables_Num.add(14);
        drawables_Num.add(12);

        add_flavor_grid = (RecyclerView)v.findViewById(R.id.add_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        add_flavor_grid.setLayoutManager(mLayoutManager);
        add_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(v.getContext(), drawables_Num);
        add_flavor_grid.setAdapter(flavorAdapter);

        add_flavor_grid.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                // RecyclerView로 전달된 TouchEvent를 받는다.
                if (e.getAction() == MotionEvent.ACTION_DOWN) {
                    View reV = rv.findChildViewUnder(e.getX(), e.getY());
                    int position = rv.getChildAdapterPosition(reV);
                    if (position >= 0) {
                        String num = String.valueOf(flavorAdapter.getNum(position));
                        if (q5_state == false) {
                            flavorAdapter.setDrawable(position);
                            q5_result = num;
                            q5_position = position;
                            q5_state = true;
                        } else if (q5_state == true && (!num.equals(q5_result))) {
                            flavorAdapter.setBackDrawable(q5_position);
                            flavorAdapter.setDrawable(position);
                            q5_result = num;
                            q5_position = position;
                        }
                        ((QuestionActivity) QuestionActivity.context).nextPage(4, q5_state, q5_result);
                    }
                }
                return false;
            }
            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            }
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });

        flavorAdapter.notifyDataSetChanged();
    }

    // 스파이시
    private void flavor_spicy(View v) {
        q5_frame4.setImageResource(R.mipmap.question_5_text_spicy);
        drawables_Num = new ArrayList<>();

        drawables_Num.add(7);
        drawables_Num.add(6);
        drawables_Num.add(9);
        drawables_Num.add(8);
        drawables_Num.add(11);
        drawables_Num.add(2);
        drawables_Num.add(4);
        drawables_Num.add(3);
        drawables_Num.add(14);

        add_flavor_grid = (RecyclerView)v.findViewById(R.id.add_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        add_flavor_grid.setLayoutManager(mLayoutManager);
        add_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(v.getContext(), drawables_Num);
        add_flavor_grid.setAdapter(flavorAdapter);

        add_flavor_grid.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                // RecyclerView로 전달된 TouchEvent를 받는다.
                if (e.getAction() == MotionEvent.ACTION_DOWN) {
                    View reV = rv.findChildViewUnder(e.getX(), e.getY());
                    int position = rv.getChildAdapterPosition(reV);
                    if (position >= 0) {
                        String num = String.valueOf(flavorAdapter.getNum(position));
                        if (q5_state == false) {
                            flavorAdapter.setDrawable(position);
                            q5_result = num;
                            q5_position = position;
                            q5_state = true;
                        } else if (q5_state == true && (!num.equals(q5_result))) {
                            flavorAdapter.setBackDrawable(q5_position);
                            flavorAdapter.setDrawable(position);
                            q5_result = num;
                            q5_position = position;
                        }
                        ((QuestionActivity) QuestionActivity.context).nextPage(4, q5_state, q5_result);
                    }
                }
                return false;
            }
            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            }
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });

        flavorAdapter.notifyDataSetChanged();
    }

    // 시프레
    private void flavor_chypre(View v) {
        q5_frame4.setImageResource(R.mipmap.question_5_text_chypre);
        drawables_Num = new ArrayList<>();

        drawables_Num.add(7);
        drawables_Num.add(6);
        drawables_Num.add(9);
        drawables_Num.add(8);
        drawables_Num.add(11);
        drawables_Num.add(2);
        drawables_Num.add(12);

        add_flavor_grid = (RecyclerView)v.findViewById(R.id.add_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        add_flavor_grid.setLayoutManager(mLayoutManager);
        add_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(v.getContext(), drawables_Num);
        add_flavor_grid.setAdapter(flavorAdapter);

        add_flavor_grid.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                // RecyclerView로 전달된 TouchEvent를 받는다.
                if (e.getAction() == MotionEvent.ACTION_DOWN) {
                    View reV = rv.findChildViewUnder(e.getX(), e.getY());
                    int position = rv.getChildAdapterPosition(reV);
                    if (position >= 0) {
                        String num = String.valueOf(flavorAdapter.getNum(position));
                        if (q5_state == false) {
                            flavorAdapter.setDrawable(position);
                            q5_result = num;
                            q5_position = position;
                            q5_state = true;
                        } else if (q5_state == true && (!num.equals(q5_result))) {
                            flavorAdapter.setBackDrawable(q5_position);
                            flavorAdapter.setDrawable(position);
                            q5_result = num;
                            q5_position = position;
                        }
                        ((QuestionActivity) QuestionActivity.context).nextPage(4, q5_state, q5_result);
                    }
                }
                return false;
            }
            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            }
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });

        flavorAdapter.notifyDataSetChanged();
    }

    // 우디
    private void flavor_woody(View v) {
        q5_frame4.setImageResource(R.mipmap.question_5_text_woody);
        drawables_Num = new ArrayList<>();

        drawables_Num.add(7);
        drawables_Num.add(9);
        drawables_Num.add(8);
        drawables_Num.add(2);
        drawables_Num.add(11);
        drawables_Num.add(4);
        drawables_Num.add(13);
        drawables_Num.add(10);
        drawables_Num.add(3);
        drawables_Num.add(14);

        add_flavor_grid = (RecyclerView)v.findViewById(R.id.add_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        add_flavor_grid.setLayoutManager(mLayoutManager);
        add_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(v.getContext(), drawables_Num);
        add_flavor_grid.setAdapter(flavorAdapter);

        add_flavor_grid.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                // RecyclerView로 전달된 TouchEvent를 받는다.
                if (e.getAction() == MotionEvent.ACTION_DOWN) {
                    View reV = rv.findChildViewUnder(e.getX(), e.getY());
                    int position = rv.getChildAdapterPosition(reV);
                    if (position >= 0) {
                        String num = String.valueOf(flavorAdapter.getNum(position));
                        if (q5_state == false) {
                            flavorAdapter.setDrawable(position);
                            q5_result = num;
                            q5_position = position;
                            q5_state = true;
                        } else if (q5_state == true && (!num.equals(q5_result))) {
                            flavorAdapter.setBackDrawable(q5_position);
                            flavorAdapter.setDrawable(position);
                            q5_result = num;
                            q5_position = position;
                        }
                        ((QuestionActivity) QuestionActivity.context).nextPage(4, q5_state, q5_result);
                    }
                }
                return false;
            }
            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            }
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });

        flavorAdapter.notifyDataSetChanged();
    }

    // 그린
    private void flavor_green(View v) {
        q5_frame4.setImageResource(R.mipmap.question_5_text_green);
        drawables_Num = new ArrayList<>();

        drawables_Num.add(6);
        drawables_Num.add(9);
        drawables_Num.add(8);
        drawables_Num.add(11);
        drawables_Num.add(10);
        drawables_Num.add(3);
        drawables_Num.add(5);
        drawables_Num.add(12);

        add_flavor_grid = (RecyclerView)v.findViewById(R.id.add_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        add_flavor_grid.setLayoutManager(mLayoutManager);
        add_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(v.getContext(), drawables_Num);
        add_flavor_grid.setAdapter(flavorAdapter);

        add_flavor_grid.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                // RecyclerView로 전달된 TouchEvent를 받는다.
                if (e.getAction() == MotionEvent.ACTION_DOWN) {
                    View reV = rv.findChildViewUnder(e.getX(), e.getY());
                    int position = rv.getChildAdapterPosition(reV);
                    if (position >= 0) {
                        String num = String.valueOf(flavorAdapter.getNum(position));
                        if (q5_state == false) {
                            flavorAdapter.setDrawable(position);
                            q5_result = num;
                            q5_position = position;
                            q5_state = true;
                        } else if (q5_state == true && (!num.equals(q5_result))) {
                            flavorAdapter.setBackDrawable(q5_position);
                            flavorAdapter.setDrawable(position);
                            q5_result = num;
                            q5_position = position;
                        }
                        ((QuestionActivity) QuestionActivity.context).nextPage(4, q5_state, q5_result);
                    }
                }
                return false;
            }
            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            }
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });

        flavorAdapter.notifyDataSetChanged();
    }

    // 발삼
    private void flavor_balsam(View v) {
        q5_frame4.setImageResource(R.mipmap.question_5_text_balsam);
        drawables_Num = new ArrayList<>();

        drawables_Num.add(9);
        drawables_Num.add(8);
        drawables_Num.add(11);
        drawables_Num.add(2);
        drawables_Num.add(4);
        drawables_Num.add(10);
        drawables_Num.add(14);
        drawables_Num.add(3);

        add_flavor_grid = (RecyclerView)v.findViewById(R.id.add_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        add_flavor_grid.setLayoutManager(mLayoutManager);
        add_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(v.getContext(), drawables_Num);
        add_flavor_grid.setAdapter(flavorAdapter);

        add_flavor_grid.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                // RecyclerView로 전달된 TouchEvent를 받는다.
                if (e.getAction() == MotionEvent.ACTION_DOWN) {
                    View reV = rv.findChildViewUnder(e.getX(), e.getY());
                    int position = rv.getChildAdapterPosition(reV);
                    if (position >= 0) {
                        String num = String.valueOf(flavorAdapter.getNum(position));
                        if (q5_state == false) {
                            flavorAdapter.setDrawable(position);
                            q5_result = num;
                            q5_position = position;
                            q5_state = true;
                        } else if (q5_state == true && (!num.equals(q5_result))) {
                            flavorAdapter.setBackDrawable(q5_position);
                            flavorAdapter.setDrawable(position);
                            q5_result = num;
                            q5_position = position;
                        }
                        ((QuestionActivity) QuestionActivity.context).nextPage(4, q5_state, q5_result);
                    }
                }
                return false;
            }
            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            }
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });

        flavorAdapter.notifyDataSetChanged();
    }

    // 아쿠아틱
    private void flavor_aquatic(View v){
        q5_frame4.setImageResource(R.mipmap.question_5_text_aquatic);
        drawables_Num = new ArrayList<>();

        drawables_Num.add(7);
        drawables_Num.add(6);
        drawables_Num.add(8);
        drawables_Num.add(11);
        drawables_Num.add(2);
        drawables_Num.add(3);
        drawables_Num.add(5);

        add_flavor_grid = (RecyclerView)v.findViewById(R.id.add_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        add_flavor_grid.setLayoutManager(mLayoutManager);
        add_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(v.getContext(), drawables_Num);
        add_flavor_grid.setAdapter(flavorAdapter);

        add_flavor_grid.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                // RecyclerView로 전달된 TouchEvent를 받는다.
                if (e.getAction() == MotionEvent.ACTION_DOWN) {
                    View reV = rv.findChildViewUnder(e.getX(), e.getY());
                    int position = rv.getChildAdapterPosition(reV);
                    if (position >= 0) {
                        String num = String.valueOf(flavorAdapter.getNum(position));
                        if (q5_state == false) {
                            flavorAdapter.setDrawable(position);
                            q5_result = num;
                            q5_position = position;
                            q5_state = true;
                        } else if (q5_state == true && (!num.equals(q5_result))) {
                            flavorAdapter.setBackDrawable(q5_position);
                            flavorAdapter.setDrawable(position);
                            q5_result = num;
                            q5_position = position;
                        }
                        ((QuestionActivity) QuestionActivity.context).nextPage(4, q5_state, q5_result);
                    }
                }
                return false;
            }
            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            }
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });

        flavorAdapter.notifyDataSetChanged();
    }

    // 너티
    private void flavor_nutty(View v){
        q5_frame4.setImageResource(R.mipmap.question_5_text_nutty);
        drawables_Num = new ArrayList<>();

        drawables_Num.add(6);
        drawables_Num.add(9);
        drawables_Num.add(8);
        drawables_Num.add(11);
        drawables_Num.add(2);
        drawables_Num.add(4);
        drawables_Num.add(10);

        add_flavor_grid = (RecyclerView)v.findViewById(R.id.add_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        add_flavor_grid.setLayoutManager(mLayoutManager);
        add_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(v.getContext(), drawables_Num);
        add_flavor_grid.setAdapter(flavorAdapter);

        add_flavor_grid.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                // RecyclerView로 전달된 TouchEvent를 받는다.
                if (e.getAction() == MotionEvent.ACTION_DOWN) {
                    View reV = rv.findChildViewUnder(e.getX(), e.getY());
                    int position = rv.getChildAdapterPosition(reV);
                    if (position >= 0) {
                        String num = String.valueOf(flavorAdapter.getNum(position));
                        if (q5_state == false) {
                            flavorAdapter.setDrawable(position);
                            q5_result = num;
                            q5_position = position;
                            q5_state = true;
                        } else if (q5_state == true && (!num.equals(q5_result))) {
                            flavorAdapter.setBackDrawable(q5_position);
                            flavorAdapter.setDrawable(position);
                            q5_result = num;
                            q5_position = position;
                        }
                        ((QuestionActivity) QuestionActivity.context).nextPage(4, q5_state, q5_result);
                    }
                }
                return false;
            }
            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            }
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });

        flavorAdapter.notifyDataSetChanged();
    }

    // 레더
    private void flavor_leather(View v){
        q5_frame4.setImageResource(R.mipmap.question_5_text_leather);
        drawables_Num = new ArrayList<>();

        drawables_Num.add(6);
        drawables_Num.add(9);
        drawables_Num.add(8);
        drawables_Num.add(11);
        drawables_Num.add(2);
        drawables_Num.add(4);
        drawables_Num.add(10);
        drawables_Num.add(3);

        add_flavor_grid = (RecyclerView)v.findViewById(R.id.add_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        add_flavor_grid.setLayoutManager(mLayoutManager);
        add_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(v.getContext(), drawables_Num);
        add_flavor_grid.setAdapter(flavorAdapter);

        add_flavor_grid.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                // RecyclerView로 전달된 TouchEvent를 받는다.
                if (e.getAction() == MotionEvent.ACTION_DOWN) {
                    View reV = rv.findChildViewUnder(e.getX(), e.getY());
                    int position = rv.getChildAdapterPosition(reV);
                    if (position >= 0) {
                        String num = String.valueOf(flavorAdapter.getNum(position));
                        if (q5_state == false) {
                            flavorAdapter.setDrawable(position);
                            q5_result = num;
                            q5_position = position;
                            q5_state = true;
                        } else if (q5_state == true && (!num.equals(q5_result))) {
                            flavorAdapter.setBackDrawable(q5_position);
                            flavorAdapter.setDrawable(position);
                            q5_result = num;
                            q5_position = position;
                        }
                        ((QuestionActivity) QuestionActivity.context).nextPage(4, q5_state, q5_result);
                    }
                }
                return false;
            }
            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            }
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });

        flavorAdapter.notifyDataSetChanged();
    }


}