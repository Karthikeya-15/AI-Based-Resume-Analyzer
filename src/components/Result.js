import React from "react";

function Result({ result }) {
  if (!result) return <div className="card">No data yet</div>;

  return (
    <div className="card">
      <h2>Analysis Result</h2>

      <p><b>File:</b> {result.name}</p>

      <p><b>Best Job Match:</b> {result.job}</p>

      <p><b>Match Score:</b> {result.score}%</p>

      <h3>Missing Skills:</h3>
      <ul>
        {(result.missingSkills || []).map((skill, i) => (
          <li key={i}>{skill}</li>
        ))}
      </ul>
    </div>
  );
}

export default Result;