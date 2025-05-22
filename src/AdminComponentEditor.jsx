import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const AdminComponentEditor = () => {
  const [components, setComponents] = useState([]);
  const [selected, setSelected] = useState(null);
  const navigate = useNavigate();

  const fetchData = () => {
    axios
      .get("http://localhost:8080/api/components")
      .then((res) => {
        console.log("🚀 컴포넌트 목록:", res.data);
        setComponents(res.data);
      })
      .catch((err) => {
        console.error("데이터 불러오기 실패:", err);
      });
  };

  useEffect(() => {
    fetchData();
  }, []);

  const handleDelete = async (id) => {
    if (window.confirm("정말 삭제하시겠습니까?")) {
      try {
        await axios.delete(`http://localhost:8080/api/components/${id}`);
        fetchData(); // 삭제 후 목록 갱신
        setSelected(null); // 선택 해제
      } catch (err) {
        console.error("삭제 실패", err);
        alert("삭제 실패: " + err.message);
      }
    }
  };

  return (
    <div style={{ padding: "1rem", display: "flex", gap: "2rem" }}>
      <div style={{ width: "40%" }}>
        <h2>📦 컴포넌트 목록</h2>
        <ul>
          {components.map((c) => (
            <li key={c.id}>
              <button
                onClick={() => setSelected(c)}
                style={{ width: "100%", textAlign: "left", marginBottom: "8px" }}
              >
                {c.categorySmall} ({c.categoryMiddle})
              </button>
            </li>
          ))}
        </ul>

        <button
          onClick={() => navigate("/guides/new")}
          style={{ marginTop: "1rem", padding: "0.5rem", backgroundColor: "#28a745", color: "#fff", border: "none" }}
        >
          ➕ 새 컴포넌트 등록
        </button>
      </div>

      <div style={{ width: "60%" }}>
        {selected && (
          <div>
            <h2>🛠️ 선택된 컴포넌트 정보</h2>
            <p><strong>대분류:</strong> {selected.categoryLarge}</p>
            <p><strong>중분류:</strong> {selected.categoryMiddle}</p>
            <p><strong>소분류:</strong> {selected.categorySmall}</p>
            <p><strong>설명:</strong></p>
            <div dangerouslySetInnerHTML={{ __html: selected.description }}
            style={{ padding: '0.5rem', backgroundColor: '#f9f9f9', borderRadius: '5px' }}
            />

            <img
              src={`data:image/png;base64,${selected.imageData}`}
              alt="이미지"
              style={{ maxWidth: "100%", marginTop: "1rem" }}
            />
            <button
              onClick={() => handleDelete(selected.id)}
              style={{ marginTop: "1rem", padding: "0.5rem", backgroundColor: "#dc3545", color: "#fff", border: "none" }}
            >
              🗑️ 삭제
            </button>
          </div>
        )}
      </div>
    </div>
  );
};

export default AdminComponentEditor;
