import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

const GuideList = () => {
  const [guides, setGuides] = useState([]);

  const fetchData = async () => {
    try {
      const res = await axios.get("http://218.234.33.15:8085/api/components");
      setGuides(res.data);
    } catch (err) {
      console.error("데이터 불러오기 실패", err);
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm("정말 삭제하시겠습니까?")) {
      try {
        await axios.delete(`http://218.234.33.15:8085/api/components/${id}`);
        fetchData();
      } catch (err) {
        console.error("삭제 실패", err);
      }
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <div className="container mt-4">
      <h2>📋 등록된 가이드</h2>
      <ul className="list-group">
        {guides.map((g) => (
          <li key={g.id} className="list-group-item">
            <div className="d-flex justify-content-between align-items-center">
              <div>
                <Link to={`/guides/${g.id}`}>
                  <b>{g.categoryLarge} &gt; {g.categoryMiddle} &gt; {g.categorySmall}</b>
                </Link>
                <div>{g.description}</div>
                <img
                  src={`http://218.234.33.15:8085/api/components/${g.id}/image`}
                  alt="guide"
                  width="150"
                  className="mt-2"
                />
              </div>
              <button
                className="btn btn-sm btn-danger"
                onClick={() => handleDelete(g.id)}
              >
                삭제
              </button>
            </div>
          </li>
        ))}
      </ul>

      <Link to="/guides/new" className="btn btn-success mt-3">
        가이드 등록
      </Link>
    </div>
  );
};

export default GuideList;
