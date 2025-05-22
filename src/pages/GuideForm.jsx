/*import React, { useState  } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const GuideForm = () => {
  const [form, setForm] = useState({
    category_large: "",
    category_middle: "",
    category_small: "",
    description: "",
  });
  const [image, setImage] = useState(null);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!form.category_large || !form.category_middle || !form.category_small || !form.description || !image) {
      alert("모든 항목과 이미지를 입력해주세요.");
      return;
    }

    const fd = new FormData();
    Object.entries(form).forEach(([key, val]) => fd.append(key, val));
    fd.append("image", image);

    try {
      await axios.post("http://localhost:8080/api/components/blob", fd);
      alert("등록 성공!");
      navigate("/"); // 등록 후 목록 페이지로 이동
    } catch (err) {
      console.error("등록 실패:", err);
      alert("등록 실패: " + err.message);
    }
  };

  return (
    <div className="container mt-4">
      <h2>📘 컴포넌트 가이드 등록</h2>
      <form onSubmit={handleSubmit}>
        <input placeholder="대분류" value={form.category_large} onChange={(e) => setForm({ ...form, category_large: e.target.value })} /><br />
        <input placeholder="중분류" value={form.category_middle} onChange={(e) => setForm({ ...form, category_middle: e.target.value })} /><br />
        <input placeholder="소분류" value={form.category_small} onChange={(e) => setForm({ ...form, category_small: e.target.value })} /><br />
        <textarea placeholder="설명" value={form.description} onChange={(e) => setForm({ ...form, description: e.target.value })} /><br />
        <input type="file" onChange={(e) => setImage(e.target.files[0])} /><br />
        <button type="submit">등록</button>
      </form>
    </div>
  );
};

export default GuideForm;*/
import React, { useState } from "react";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";
import RichTextEditor from "../components/RichTextEditor"; //
import CategorySelector from "../components/CategorySelector";
import CKEditorComponent from '../components/CKEditorComponent'; //<CKEditorComponent value={form.description} onChange={(value) => setForm({ ...form, description: value })}/>

const GuideForm = () => {
  const [form, setForm] = useState({
    categoryLarge: "",
    categoryMiddle: "",
    categorySmall: "",
    description: "",
  });
  const [image, setImage] = useState(null);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (
      !form.categoryLarge ||
      !form.categoryMiddle ||
      !form.categorySmall ||
      !form.description 
    ) {
      alert("이미지를 제외한 모든 항목을 입력해주세요.");
      return;
    }

    const fd = new FormData();
    Object.entries(form).forEach(([key, val]) => fd.append(key, val));
    fd.append("image", image);

    try {
      await axios.post("http://localhost:8080/api/components", fd);
      alert("등록 성공!");
      navigate("/");
    } catch (err) {
      console.error("등록 실패:", err);
      alert("등록 실패: " + err.message);
    }
  };

  return (
    <div className="container mt-4">
      <Link to="/" className="btn btn-secondary"> 목록으로</Link>
      <h2>📘 컴포넌트 가이드 등록</h2>
      <form onSubmit={handleSubmit}>
        <CategorySelector form={form} setForm={setForm} />
        <div className="form-group">
          <label htmlFor="description"><b>내용:</b></label>
          <RichTextEditor value={form.description} onChange={(value) => setForm({ ...form, description: value })}/>
        </div>

        <br />
        <input type="file" onChange={(e) => setImage(e.target.files[0])} /><br />
        <button type="submit">등록</button>
      </form>
    </div>
  );
};

export default GuideForm;

