package com.example.perfume;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.perfume.adapter.FlavorAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Question4#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class Question4 extends Fragment {

    public static String q4_style;
    public static Context q4_context;

    public Boolean q4_state = false;
    public String q4_result;
    public Integer q4_position;
    int result;

    View v;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView main_flavor_grid;
    FlavorAdapter flavorAdapter;

    ArrayList<Integer> drawables_Num;

    ImageView q4_frame4;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Question4.
     */
    // TODO: Rename and change types and number of parameters
    public static Question4 newInstance(String param1, String param2) {
        Question4 fragment = new Question4(q4_style);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Question4(String style) {
        // Required empty public constructor
        q4_style = style;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_question4, container, false);
        q4_context = v.getContext();
        q4_frame4 = (ImageView) v.findViewById(R.id.q4_frame4);

        // BackBTN을 이용해 뒤로 갔다 온 경우에 원래 값을 설정해야한다.
        if(((QuestionActivity)QuestionActivity.context).q_state[3] != false) {
            result = Integer.parseInt(((QuestionActivity) QuestionActivity.context).q_result[3]);
            q4_result = String.valueOf(result);
            q4_state = true;
        }
        else
            result = 0;

        // 스타일 따라 이미지 다르게 뿌려주기
        if (q4_style.equals("1"))
            style_1(v, result);
        if (q4_style.equals("2"))
            style_2(v, result);
        if (q4_style.equals("3"))
            style_3(v, result);
        if (q4_style.equals("4"))
            style_4(v, result);
        if (q4_style.equals("5"))
            style_5(v, result);
        if (q4_style.equals("6"))
            style_6(v, result);
        if (q4_style.equals("7"))
            style_7(v, result);
        if (q4_style.equals("8"))
            style_8(v, result);

        Log.v("스타일", q4_style);

        return v;
    }

    // 처음 포근한, 차분한, 따듯한, 순수한 스타일
    private void style_1(View v, final int result) {
        q4_frame4.setImageResource(R.mipmap.question_4_text_s1);
        drawables_Num = new ArrayList<>();

        drawables_Num.add(7);
        drawables_Num.add(8);
        drawables_Num.add(1);
        drawables_Num.add(11);
        drawables_Num.add(2);
        drawables_Num.add(4);
        drawables_Num.add(13);

        main_flavor_grid = (RecyclerView)v.findViewById(R.id.main_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        main_flavor_grid.setLayoutManager(mLayoutManager);
        main_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(q4_context, drawables_Num);
        main_flavor_grid.setAdapter(flavorAdapter);

        if(result != 0) {
            int num = drawables_Num.indexOf(result);
            flavorAdapter.setDrawable(num);
            q4_state = true;
            q4_result = String.valueOf(result);
        }

        main_flavor_grid.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                // RecyclerView로 전달된 TouchEvent를 받는다.
                if (e.getAction() == MotionEvent.ACTION_DOWN) {
                    View reV = rv.findChildViewUnder(e.getX(), e.getY());
                    int position = rv.getChildAdapterPosition(reV);
                    if (position >= 0) {
                        String num = String.valueOf(flavorAdapter.getNum(position));
                        if (q4_state == false) {
                            flavorAdapter.setDrawable(position);
                            q4_result = num;
                            q4_position = position;
                            q4_state = true;
                        } else if (q4_state == true && (!num.equals(q4_result))) {
                            if(result != 0)
                                ((QuestionActivity) QuestionActivity.context).deletePage(3);
                            flavorAdapter.setBackDrawable(q4_position);
                            flavorAdapter.setDrawable(position);
                            q4_result = num;
                            q4_position = position;
                        }
                        ((QuestionActivity) QuestionActivity.context).nextPage(3, q4_state, q4_result);
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

    // 발랄한, 귀여운, 사랑스러운 스타일
    private void style_2(View v, final int result) {
        q4_frame4.setImageResource(R.mipmap.question_4_text_s2);
        drawables_Num = new ArrayList<>();

        drawables_Num.add(7);
        drawables_Num.add(6);
        drawables_Num.add(9);
        drawables_Num.add(8);
        drawables_Num.add(5);
        drawables_Num.add(13);

        main_flavor_grid = (RecyclerView)v.findViewById(R.id.main_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        main_flavor_grid.setLayoutManager(mLayoutManager);
        main_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(getActivity(), drawables_Num);
        main_flavor_grid.setAdapter(flavorAdapter);

        if(result != 0) {
            int num = drawables_Num.indexOf(result);
            flavorAdapter.setDrawable(num);
            q4_state = true;
            q4_result = String.valueOf(result);
        }

        main_flavor_grid.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                // RecyclerView로 전달된 TouchEvent를 받는다.
                if (e.getAction() == MotionEvent.ACTION_DOWN) {
                    View reV = rv.findChildViewUnder(e.getX(), e.getY());
                    int position = rv.getChildAdapterPosition(reV);
                    if (position >= 0) {
                        String num = String.valueOf(flavorAdapter.getNum(position));
                        if (q4_state == false) {
                            flavorAdapter.setDrawable(position);
                            q4_result = num;
                            q4_position = position;
                            q4_state = true;
                        } else if (q4_state == true && (!num.equals(q4_result))) {
                            if(result != 0)
                                ((QuestionActivity) QuestionActivity.context).deletePage(3);
                            flavorAdapter.setBackDrawable(q4_position);
                            flavorAdapter.setDrawable(position);
                            q4_result = num;
                            q4_position = position;
                        }
                        ((QuestionActivity) QuestionActivity.context).nextPage(3, q4_state, q4_result);
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

    // 관능적인, 화려한 스타일
    private void style_3(View v, final int result) {
        q4_frame4.setImageResource(R.mipmap.question_4_text_s3);
        drawables_Num = new ArrayList<>();

        drawables_Num.add(9);
        drawables_Num.add(8);
        drawables_Num.add(1);
        drawables_Num.add(11);
        drawables_Num.add(2);
        drawables_Num.add(4);
        drawables_Num.add(10);

        main_flavor_grid = (RecyclerView)v.findViewById(R.id.main_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        main_flavor_grid.setLayoutManager(mLayoutManager);
        main_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(getActivity(), drawables_Num);
        main_flavor_grid.setAdapter(flavorAdapter);

        if(result != 0) {
            int num = drawables_Num.indexOf(result);
            flavorAdapter.setDrawable(num);
            q4_state = true;
            q4_result = String.valueOf(result);
        }

        main_flavor_grid.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                // RecyclerView로 전달된 TouchEvent를 받는다.
                if (e.getAction() == MotionEvent.ACTION_DOWN) {
                    View reV = rv.findChildViewUnder(e.getX(), e.getY());
                    int position = rv.getChildAdapterPosition(reV);
                    if (position >= 0) {
                        String num = String.valueOf(flavorAdapter.getNum(position));
                        if (q4_state == false) {
                            flavorAdapter.setDrawable(position);
                            q4_result = num;
                            q4_position = position;
                            q4_state = true;
                        } else if (q4_state == true && (!num.equals(q4_result))) {
                            if(result != 0)
                                ((QuestionActivity) QuestionActivity.context).deletePage(3);
                            flavorAdapter.setBackDrawable(q4_position);
                            flavorAdapter.setDrawable(position);
                            q4_result = num;
                            q4_position = position;
                        }
                        ((QuestionActivity) QuestionActivity.context).nextPage(3, q4_state, q4_result);
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

    // 환상적인, 황홀한, 몽환적인, 신비로운 스타일
    private void style_4(View v, final int result) {
        q4_frame4.setImageResource(R.mipmap.question_4_text_s4);
        drawables_Num = new ArrayList<>();

        drawables_Num.add(6);
        drawables_Num.add(8);
        drawables_Num.add(1);
        drawables_Num.add(2);
        drawables_Num.add(4);

        main_flavor_grid = (RecyclerView)v.findViewById(R.id.main_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        main_flavor_grid.setLayoutManager(mLayoutManager);
        main_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(getActivity(), drawables_Num);
        main_flavor_grid.setAdapter(flavorAdapter);

        if(result != 0) {
            int num = drawables_Num.indexOf(result);
            flavorAdapter.setDrawable(num);
            q4_state = true;
            q4_result = String.valueOf(result);
        }

        main_flavor_grid.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                // RecyclerView로 전달된 TouchEvent를 받는다.
                if (e.getAction() == MotionEvent.ACTION_DOWN) {
                    View reV = rv.findChildViewUnder(e.getX(), e.getY());
                    int position = rv.getChildAdapterPosition(reV);
                    if (position >= 0) {
                        String num = String.valueOf(flavorAdapter.getNum(position));
                        if (q4_state == false) {
                            flavorAdapter.setDrawable(position);
                            q4_result = num;
                            q4_position = position;
                            q4_state = true;
                        } else if (q4_state == true && (!num.equals(q4_result))) {
                            if(result != 0)
                                ((QuestionActivity) QuestionActivity.context).deletePage(3);
                            flavorAdapter.setBackDrawable(q4_position);
                            flavorAdapter.setDrawable(position);
                            q4_result = num;
                            q4_position = position;
                        }
                        ((QuestionActivity) QuestionActivity.context).nextPage(3, q4_state, q4_result);
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

    // 젠틀한, 클래식한, 깊이있는 스타일
    private void style_5(View v, final int result) {
        q4_frame4.setImageResource(R.mipmap.question_4_text_s5);
        drawables_Num = new ArrayList<>();

        drawables_Num.add(7);
        drawables_Num.add(6);
        drawables_Num.add(11);
        drawables_Num.add(2);
        drawables_Num.add(4);
        drawables_Num.add(3);
        drawables_Num.add(14);
        drawables_Num.add(13);

        main_flavor_grid = (RecyclerView)v.findViewById(R.id.main_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        main_flavor_grid.setLayoutManager(mLayoutManager);
        main_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(getActivity(), drawables_Num);
        main_flavor_grid.setAdapter(flavorAdapter);

        if(result != 0) {
            int num = drawables_Num.indexOf(result);
            flavorAdapter.setDrawable(num);
            q4_state = true;
            q4_result = String.valueOf(result);
        }

        main_flavor_grid.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                // RecyclerView로 전달된 TouchEvent를 받는다.
                if (e.getAction() == MotionEvent.ACTION_DOWN) {
                    View reV = rv.findChildViewUnder(e.getX(), e.getY());
                    int position = rv.getChildAdapterPosition(reV);
                    if (position >= 0) {
                        String num = String.valueOf(flavorAdapter.getNum(position));
                        if (q4_state == false) {
                            flavorAdapter.setDrawable(position);
                            q4_result = num;
                            q4_position = position;
                            q4_state = true;
                        } else if (q4_state == true && (!num.equals(q4_result))) {
                            if(result != 0)
                                ((QuestionActivity) QuestionActivity.context).deletePage(3);
                            flavorAdapter.setBackDrawable(q4_position);
                            flavorAdapter.setDrawable(position);
                            q4_result = num;
                            q4_position = position;
                        }
                        ((QuestionActivity) QuestionActivity.context).nextPage(3, q4_state, q4_result);
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

    // 세련된, 우아한, 도시적인, 모던한 스타일
    private void style_6(View v, final int result) {
        q4_frame4.setImageResource(R.mipmap.question_4_text_s6);
        drawables_Num = new ArrayList<>();

        drawables_Num.add(7);
        drawables_Num.add(6);
        drawables_Num.add(8);
        drawables_Num.add(11);
        drawables_Num.add(2);
        drawables_Num.add(4);
        drawables_Num.add(13);
        drawables_Num.add(5);

        main_flavor_grid = (RecyclerView)v.findViewById(R.id.main_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        main_flavor_grid.setLayoutManager(mLayoutManager);
        main_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(getActivity(), drawables_Num);
        main_flavor_grid.setAdapter(flavorAdapter);

        if(result != 0) {
            int num = drawables_Num.indexOf(result);
            flavorAdapter.setDrawable(num);
            q4_state = true;
            q4_result = String.valueOf(result);
        }

        main_flavor_grid.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                // RecyclerView로 전달된 TouchEvent를 받는다.
                if (e.getAction() == MotionEvent.ACTION_DOWN) {
                    View reV = rv.findChildViewUnder(e.getX(), e.getY());
                    int position = rv.getChildAdapterPosition(reV);
                    if (position >= 0) {
                        String num = String.valueOf(flavorAdapter.getNum(position));
                        if (q4_state == false) {
                            flavorAdapter.setDrawable(position);
                            q4_result = num;
                            q4_position = position;
                            q4_state = true;
                        } else if (q4_state == true && (!num.equals(q4_result))) {
                            if(result != 0)
                                ((QuestionActivity) QuestionActivity.context).deletePage(3);
                            flavorAdapter.setBackDrawable(q4_position);
                            flavorAdapter.setDrawable(position);
                            q4_result = num;
                            q4_position = position;
                        }
                        ((QuestionActivity) QuestionActivity.context).nextPage(3, q4_state, q4_result);
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

    // 산뜻한, 시원한, 활기찬 스타일
    private void style_7(View v, final int result) {
        q4_frame4.setImageResource(R.mipmap.question_4_text_s7);
        drawables_Num = new ArrayList<>();

        drawables_Num.add(7);
        drawables_Num.add(6);
        drawables_Num.add(9);
        drawables_Num.add(3);
        drawables_Num.add(10);
        drawables_Num.add(5);
        drawables_Num.add(12);

        main_flavor_grid = (RecyclerView)v.findViewById(R.id.main_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        main_flavor_grid.setLayoutManager(mLayoutManager);
        main_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(getActivity(), drawables_Num);
        main_flavor_grid.setAdapter(flavorAdapter);

        if(result != 0) {
            int num = drawables_Num.indexOf(result);
            flavorAdapter.setDrawable(num);
            q4_state = true;
            q4_result = String.valueOf(result);
        }

        main_flavor_grid.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                // RecyclerView로 전달된 TouchEvent를 받는다.
                if (e.getAction() == MotionEvent.ACTION_DOWN) {
                    View reV = rv.findChildViewUnder(e.getX(), e.getY());
                    int position = rv.getChildAdapterPosition(reV);
                    if (position >= 0) {
                        String num = String.valueOf(flavorAdapter.getNum(position));
                        if (q4_state == false) {
                            flavorAdapter.setDrawable(position);
                            q4_result = num;
                            q4_position = position;
                            q4_state = true;
                        } else if (q4_state == true && (!num.equals(q4_result))) {
                            if(result != 0)
                                ((QuestionActivity) QuestionActivity.context).deletePage(3);
                            flavorAdapter.setBackDrawable(q4_position);
                            flavorAdapter.setDrawable(position);
                            q4_result = num;
                            q4_position = position;
                        }
                        ((QuestionActivity) QuestionActivity.context).nextPage(3, q4_state, q4_result);
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

    // 강렬한, 파워풀한, 존재감있는, 대담한 스타일
    private void style_8(View v, final int result) {
        q4_frame4.setImageResource(R.mipmap.question_4_text_s8);
        drawables_Num = new ArrayList<>();

        drawables_Num.add(6);
        drawables_Num.add(11);
        drawables_Num.add(2);
        drawables_Num.add(4);
        drawables_Num.add(3);
        drawables_Num.add(10);
        drawables_Num.add(14);
        drawables_Num.add(5);

        main_flavor_grid = (RecyclerView)v.findViewById(R.id.main_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        main_flavor_grid.setLayoutManager(mLayoutManager);
        main_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(getActivity(), drawables_Num);
        main_flavor_grid.setAdapter(flavorAdapter);

        if(result != 0) {
            int num = drawables_Num.indexOf(result);
            flavorAdapter.setDrawable(num);
            q4_state = true;
            q4_result = String.valueOf(result);
        }

        main_flavor_grid.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                // RecyclerView로 전달된 TouchEvent를 받는다.
                if (e.getAction() == MotionEvent.ACTION_DOWN) {
                    View reV = rv.findChildViewUnder(e.getX(), e.getY());
                    int position = rv.getChildAdapterPosition(reV);
                    if (position >= 0) {
                        String num = String.valueOf(flavorAdapter.getNum(position));
                        if (q4_state == false) {
                            flavorAdapter.setDrawable(position);
                            q4_result = num;
                            q4_position = position;
                            q4_state = true;
                        } else if (q4_state == true && (!num.equals(q4_result))) {
                            if(result != 0)
                                ((QuestionActivity) QuestionActivity.context).deletePage(3);
                            flavorAdapter.setBackDrawable(q4_position);
                            flavorAdapter.setDrawable(position);
                            q4_result = num;
                            q4_position = position;
                        }
                        ((QuestionActivity) QuestionActivity.context).nextPage(3, q4_state, q4_result);
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