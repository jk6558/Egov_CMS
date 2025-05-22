import { Routes, Route } from "react-router-dom";
import GuideList from "./pages/GuideList";
import GuideForm from "./pages/GuideForm";
import AdminComponentEditor from "./AdminComponentEditor"; 

function App() {
  return (
    <Routes>
      <Route path="/guides" element={<GuideList />} />
      <Route path="/guides/new" element={<GuideForm />} />
      <Route path="/" element={<AdminComponentEditor />} />
    </Routes>
  );
}

export default App;
