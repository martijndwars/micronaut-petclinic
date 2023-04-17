import { useEffect, useState } from 'react';
import Link from 'next/link';

export default function Owners() {
  const [owners, setOwners] = useState<any[]>([]);

  const fetchData = () => {
    fetch(`http://localhost:8080/owners`)
      .then((response) => response.json())
      .then(setOwners)
      .catch((err) => {
        console.log(err.message);
      });
  }

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <div>
      <h1>Owners</h1>
      <table className="table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Address</th>
            <th>City</th>
            <th>Telephone</th>
            <th>Pets</th>
          </tr>
        </thead>
        <tbody>
          {owners.map((owner, index) => (
            <tr key={index}>
              <td>
                <Link href={`/owners/${owner.id}`}>{owner.firstName} {owner.lastName}</Link>
              </td>
              <td>{owner.address}</td>
              <td>{owner.city}</td>
              <td>{owner.telephone}</td>
              <td>{owner.pets}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <Link className="btn btn-success" href="/owners/add">Add owner</Link>
    </div>
  )
}
