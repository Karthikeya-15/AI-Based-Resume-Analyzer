import React, { useState } from "react";
import Upload from "./components/Upload";
import Result from "./components/Result";
import "./App.css";

function App() {
  const [result, setResult] = useState(null);

  return (
    <div className="container">
      <h1>AI Resume Analyzer</h1>

      <div className="grid">
        <Upload setResult={setResult} />
        <Result result={result} />
      </div>
    </div>
  );
}

export default App;