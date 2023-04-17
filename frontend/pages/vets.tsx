import { useEffect, useState } from "react";

export default function Vets() {
  const [vets, setVets] = useState<any[]>([]);

  const fetchData = () => {
    fetch(`http://localhost:8080/vets`)
      .then((response) => response.json())
      .then(setVets)
      .catch((err) => {
        console.log(err.message);
      });
  }

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <div>
      <h1>Veterinarians</h1>
      <table className="table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Specialties</th>
          </tr>
        </thead>
        <tbody>
          {vets.map((vet, index) => (
            <tr key={index}>
              <td>{vet.name}</td>
              <td>n/a</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}
