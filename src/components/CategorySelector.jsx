// components/CategorySelector.jsx
// Tiptap
import React from 'react';

const CategorySelector = ({ form, setForm }) => {
  return (
    <div className="mb-3">
      <label className="form-label">대분류</label>
      <input
        type="text"
        className="form-control"
        placeholder="예: 사용자"
        value={form.categoryLarge}
        onChange={(e) => setForm({ ...form, categoryLarge: e.target.value })}
      />

      <label className="form-label mt-3">중분류</label>
      <input
        type="text"
        className="form-control"
        placeholder="예: 가입"
        value={form.categoryMiddle}
        onChange={(e) => setForm({ ...form, categoryMiddle: e.target.value })}
      />

      <label className="form-label mt-3">소분류</label>
      <input
        type="text"
        className="form-control"
        placeholder="예: 약관관리"
        value={form.categorySmall}
        onChange={(e) => setForm({ ...form, categorySmall: e.target.value })}
      />
    </div>
  );
};

export default CategorySelector;
