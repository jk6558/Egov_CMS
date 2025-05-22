/*import React, { useState, useEffect } from "react";
import axios from "axios";

function App() {
  const [form, setForm] = useState({
    category_large: "",
    category_middle: "",
    category_small: "",
    description: "",
  });
  const [image, setImage] = useState(null);
  const [guides, setGuides] = useState([]);

  const fetchData = async () => {
    const res = await axios.get("http://localhost:8080/api/components/blob");
    setGuides(res.data);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
  
    if (!form.category_large || !form.category_middle || !form.category_small || !form.description) {
      alert("모든 항목을 입력해주세요.");
      return;
    }
  
    if (!image) {
      alert("이미지를 선택해주세요.");
      return;
    }
  
    const fd = new FormData();
    fd.append("category_large", form.category_large);
    fd.append("category_middle", form.category_middle);
    fd.append("category_small", form.category_small);
    fd.append("description", form.description);
    fd.append("image", image);
  
    try {
      await axios.post("http://localhost:8080/api/components/blob", fd);
      setForm({ category_large: "", category_middle: "", category_small: "", description: "" });
      setImage(null);
      fetchData();
    } catch (err) {
      console.error("업로드 실패:", err);
      alert("등록 실패: " + err.message);
    }
  };
  

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <div style={{ padding: 20 }}>
      <h2>📘 컴포넌트 가이드 등록 (이미지 포함)</h2>
      <form onSubmit={handleSubmit}>
        <input placeholder="대분류" value={form.category_large} onChange={(e) => setForm({ ...form, category_large: e.target.value })} /><br />
        <input placeholder="중분류" value={form.category_middle} onChange={(e) => setForm({ ...form, category_middle: e.target.value })} /><br />
        <input placeholder="소분류" value={form.category_small} onChange={(e) => setForm({ ...form, category_small: e.target.value })} /><br />
        <textarea placeholder="설명" value={form.description} onChange={(e) => setForm({ ...form, description: e.target.value })} /><br />
        <input type="file" onChange={(e) => setImage(e.target.files[0])} /><br />
        <button type="submit">등록</button>
      </form>

      <h3>📋 등록된 가이드</h3>
      <ul>
        {guides.map((g) => (
          <li key={g.id}>
            <b>{g.categoryLarge} {'>'} {g.categoryMiddle} {'>'} {g.categorySmall}</b><br />
            {g.description}<br />
            <img src={`http://localhost:8080/api/components/blob/${g.id}/image`} alt="guide" width="150" /><hr />
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
*/