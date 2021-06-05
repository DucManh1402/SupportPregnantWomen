package com.example.test.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.Adapter.Adapter;
import com.example.test.Model.Nutrition;
import com.example.test.R;

import java.util.ArrayList;
import java.util.List;


public class MenuFragment extends Fragment {

    RecyclerView recyclerView;

    List<Nutrition> nutList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        initData();
        initRecyclerView();

        return view;
    }
    private void initRecyclerView() {
        Adapter adapter = new Adapter(nutList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        nutList = new ArrayList<>();
        nutList.add(new Nutrition("Ba tháng đầu "," ","Ở 3 tháng đầu của thai kỳ, nhiều trường hợp mẹ bầu bị ốm nghén, luôn cảm thấy khó chịu, thậm chí buồn nôn mỗi khi nhìn thấy thức ăn. Nhưng vì đây là giai đoạn hầu hết các cơ quan quan trọng của phôi được hình thành, nên dù không ăn được nhiều, mẹ bầu vẫn cần đảm bảo chế độ dinh dưỡng đủ chất bằng cách ăn uống đa dạng thực phẩm, đặc biệt ăn nhiều rau xanh, trái cây…\n" +
                "\n" +
                "Nếu trước khi mang thai mẹ bầu chưa bổ sung acid folic thì từ ngày đầu tiên biết mình mang thai cần bổ sung ngay. Liều lượng khuyến cáo là 400 mcg/ngày. Bên cạnh đó, sắt và canxi cũng cần được tăng cường trong suốt 9 tháng mang thai nhằm tránh thiếu máu và loãng xương cho mẹ về sau. Bà bầu có thể sử dụng loại vitamin tổng hợp, trong thành phần có chứa acid folic, sắt, canxi theo chỉ định của bác sĩ.\n" +
                "\n" +
                "Thai nhi trong giai đoạn này rất nhạy cảm với các tác nhân từ bên ngoài như vi khuẩn, virus, rượu, thuốc, chất kích thích, hóa chất… Do đó, bà bầu cần kiêng sử dụng hay tiếp xúc với những tác nhân này và thiết lập cũng như duy trì một chế độ dinh dưỡng lành mạnh khi mang thai.\n" +
                "\n" +
                "Việc uống thuốc chữa bệnh trong 3 tháng đầu cần đặc biệt lưu ý theo chỉ định của bác sĩ để tránh ảnh hưởng xấu đến sự phát triển của thai nhi. Trên thực tế, nhiều trường hợp đáng tiếc đã xảy ra như mẹ tự ý dùng thuốc kháng sinh khi bị cảm trong 3 tháng đầu hay nhiễm virus Rubella khiến thai nhi bị dị tật bẩm sinh… Để giảm nguy cơ mắc bệnh truyền nhiễm, mẹ bầu nên tiêm phòng trước khi mang thai và trong thai kỳ đầy đủ, đồng thời hạn chế đến chỗ đông người.\n" +
                "\n" +
                "Ngoài ra việc thực hiện các xét nghiệm cần thiết khi mang thai cũng giúp mẹ bầu phát hiện nhanh chóng các căn bệnh thai kỳ phổ biến."));
        nutList.add(new Nutrition("Ba tháng tiếp theo", " ", " Đây là khoảng thời gian dễ chịu nhất trong hành trình 9 tháng 10 ngày mang thai, đa số bà bầu không còn bị cảm giác ốm nghén hành hạ nên việc ăn uống cảm thấy ngon miệng hơn. Về phía thai nhi, lúc này hệ xương phát triển mạnh, não bộ và các cơ quan cũng dần hoàn thiện chức năng. Do đó ngoài acid folic, sắt, canxi, bà bầu cần bổ sung thực phẩm có chứa kẽm, liều lượng 20mg/ngày. Việc thiếu kẽm khiến thai nhi nhẹ cân, chiều cao thấp, dị tật…\n" +
                "\n" +
                "Mẹ bầu không nên có suy nghĩ phải ăn gấp đôi, gấp 3 bình thường để “con to” bởi lúc này thai nhi vẫn chưa bước sang thời kỳ “bứt phá” về cân nặng (đến 26 tuần tuổi, thai nhi chỉ mới nặng khoảng 900g). Theo khuyến cáo, trong 3 tháng giữa của thai kỳ, mẹ bầu chỉ cần tăng khẩu phần ăn lên tương đương khoảng 300 – 400 kcal/ngày (bằng 2 chén cơm trắng hoặc 2 ly sữa).\n" +
                "\n" +
                "Nếu ăn uống quá nhiều, mẹ tăng cân quá mức không chỉ ảnh hưởng đến vóc dáng, tâm lý sau sinh mà còn tăng nguy cơ tiểu đường, tắng huyết áp, tiền sản giật trong thai kỳ."));
        nutList.add(new Nutrition("Ba tháng cuối", "", "Giai đoạn 3 tháng cuối của thai kỳ đánh dấu bước phát triển vượt bậc về cân nặng của thai nhi. Để thai nhi tăng cân tốt, mẹ bầu cần chú ý đến tăng khẩu phần khoảng 400 kcal/ngày.\n" +
                "\n" +
                "Lúc này, mẹ bầu cần bổ sung vitamin C cho cơ thể, nhằm hấp thụ sắt và canxi tốt hơn đồng thời tránh nguy cơ vỡ ối và sinh non (do thiếu vitamin C).\n" +
                "\n" +
                "Vào 3 tháng cuối, do sự thay đổi hormone và thai nhi lớn gây áp lực lên vùng chậu và bàng quang khiến mẹ bầu thường bị táo bón, đầy bụng. Để tránh tình trạng này, chế độ ăn cho bà bầu nên bổ sung nhiều chất xơ và tránh ăn các thực phẩm khó tiêu hóa.\n" +
                "\n" +
                "Như vậy, trong hành trình 9 tháng mang thai, có những giai đoạn mẹ bầu không cần tăng khẩu phần so với bình thường mà nên chú trọng đến nhóm chất bổ sung. Ngoài ra, với những mẹ bầu có nhiều nguy cơ trong thai kỳ, mẹ bầu ăn chay…, chế độ dinh dưỡng thai kỳ còn cần “thiết kế” kỹ càng, chi tiết theo từng tuần để vừa đảm bảo sức khỏe cho mẹ, vừa giúp thai nhi phát triển tốt nhất."));
        nutList.add(new Nutrition("Những thức ăn cần tránh", "", "Trong mỗi giai đoạn phát triển của bào thai, mẹ bầu cần có một chế độ dinh dưỡng phù hợp để thai nhi phát triển tối ưu. Nhưng dù ở 3 tháng đầu, 3 tháng giữa hay 3 tháng cuối, thực đơn của mẹ cần tránh các loại thực phẩm, đồ uống sau:\n" +
                "\n\n" +
                "Rượu\n\n" +
                "Một hậu quả nghiêm trọng của việc uống rượu, bia khi mang thai là gây ra hội chứng rối loạn do nhiễm độc rượu bào thai (Fetal alcohol spectrum disorders – FASD). Đây là căn bệnh gây hệ lụy suốt đời, khiến thai nhi kém phát triển (ngay từ trong tử cung, sau khi sinh, hoặc cả hai), các đặc điểm trên khuôn mặt bất thường, dị tật tim và tổn thương hệ thần kinh trung ương. Những em bé bị mắc hội chứng FASD cũng có thể có đầu và não nhỏ bất thường, các khuyết tật bẩm sinh khác, đặc biệt là tim và cột sống.\n" +
                "\n" +
                "Cá có hàm lượng thủy ngân cao\n\n" +
                "Các loại hải sản như cá kiếm, cá mập, cá thu, cá mòi, cá nhám da cam và cá ngói có hàm lượng metyl thủy ngân cao, có thể đi qua nhau thai và gây hại cho não, thận và hệ thần kinh đang phát triển của thai nhi.\n" +
                "\n" +
                "Cá, thịt, trứng sống hoặc chưa nấu chín\n\n" +
                "Các thực phẩm sống đều có thể bị nhiễm khuẩn, tiềm ẩn nguy cơ gây ra một số bệnh nhiễm trùng và dẫn đến sinh non, sảy thai, thai chết lưu và các vấn đề sức khỏe nghiêm trọng khác cho bà bầu. Ăn thịt chưa nấu chín cũng làm tăng nguy cơ nhiễm trùng. Vi khuẩn có thể đe dọa sức khỏe của thai nhi, có thể dẫn đến thai chết lưu hoặc các bệnh thần kinh nghiêm trọng, bao gồm khuyết tật trí tuệ, mù lòa và động kinh.\n" +
                "\n" +
                "Caffeine\n\n" +
                "Caffeine được tìm thấy trong cà phê, trà, nước ngọt và ca cao. Lượng caffeine cao trong thai kỳ đã được chứng minh là hạn chế sự phát triển của thai nhi và làm tăng nguy cơ cân nặng khi sinh thấp. Nó cũng tăng nguy cơ tử vong ở trẻ sơ sinh và nguy cơ mắc các bệnh mãn tính ở tuổi trưởng thành.\n" +
                "\n" +
                "Sữa, nước ép trái cây chưa tiệt trùng, phô mai\n\n" +
                "Sữa tươi, phô mai, nước trái cây chưa tiệt trùng có thể chứa một loạt vi khuẩn có hại, dẫn đến những bệnh nhiễm trùng, đe dọa đến tính mạng đối với em bé chưa sinh.\n" +
                "\n" +
                "Sản phẩm chưa rửa\n\n" +
                "Bề mặt của các loại trái cây và rau quả chưa rửa hoặc chưa gọt vỏ có thể bị nhiễm một số vi khuẩn, ký sinh trùng, hóa chất bảo quản gây hại cho cả mẹ và thai nhi. Để giảm thiểu nguy cơ nhiễm trùng, mẹ bầu nên đừng quên rửa kỹ, gọt vỏ các loại trái cây và rau quả trước khi ăn. \n" +
                "\n" +
                "Thực phẩm chế biến sẵn\n\n" +
                "Đồ ăn vặt chế biến sẵn thường có ít chất dinh dưỡng và nhiều calo, đường. Chúng tăng nguy cơ mắc bệnh tiểu đường thai kỳ, cũng như các biến chứng khi mang thai hoặc sinh. Điều này gây ra các vấn đề sức khỏe lâu dài cho trẻ nhỏ."));
        nutList.add(new Nutrition("Chế độ vận động", "", "Ngoài chế độ dinh dưỡng cho mẹ bầu, tập thể dục cũng là một phương thức quan trọng tăng cường sức khỏe cho mẹ trong quá trình mang thai, tuy nhiên cần chú ý về thời lượng tập và tránh các động tác quá mạnh. Theo nhiều nghiên cứu y khoa, tập thể dục giúp mẹ và thai nhi khỏe mạnh, chống lại các bệnh như cảm lạnh…; đồng thời “vượt cạn” nhẹ nhàng hơn và sinh con khỏe mạnh. Mẹ bầu có thể thực hiện các bài tập nhẹ nhàng và đi bộ từ 15 đến 20 phút/ngày tùy vào tình trạng sức khỏe của mình."));


    }
}