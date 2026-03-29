import React, { useState } from "react";
import axios from "axios";

function Upload({ setResult }) {
  const [file, setFile] = useState(null);

  const uploadResume = async () => {
    const formData = new FormData();
    formData.append("file", file);

    const res = await axios.post(
      "http://localhost:8080/resume/upload",
      formData
    );

    setResult(res.data);
  };

  return (
    <div className="card">
      <h2>Upload Resume</h2>
      <input type="file" onChange={(e) => setFile(e.target.files[0])} />
      <button onClick={uploadResume}>Analyze</button>
    </div>
  );
}

export default Upload;