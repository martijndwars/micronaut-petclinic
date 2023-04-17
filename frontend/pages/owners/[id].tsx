import { useRouter } from 'next/router';
import { useEffect, useState } from 'react';
import Link from 'next/link';

export default function OwnerView() {
  const router = useRouter();
  const { id } = router.query;

  const [owner, setOwner] = useState({
    firstName: '',
    lastName: '',
    address: '',
    city: '',
    telephone: ''
  });

  const [pets, setPets] = useState<any[]>([]);

  const fetchOwner = () => {
    fetch(`http://localhost:8080/owners/${id}`)
      .then((response) => response.json())
      .then(setOwner)
      .catch((err) => {
        console.log(err.message);
      });

    fetch(`http://localhost:8080/pets?ownerId=${id}`)
      .then((response) => response.json())
      .then(setPets)
      .catch((err) => {
        console.log(err.message);
      });
  }

  useEffect(() => {
    if (id == undefined) {
      return;
    }
    fetchOwner();
  }, [id]);

  return (
    <div>
      <h1>Owner Information</h1>
      <table className="table">
        <tbody>
          <tr>
            <td>Name</td>
            <td>{owner.firstName} {owner.lastName}</td>
          </tr>
          <tr>
            <td>Address</td>
            <td>{owner.address}</td>
          </tr>
          <tr>
            <td>City</td>
            <td>{owner.city}</td>
          </tr>
          <tr>
            <td>Telephone</td>
            <td>{owner.telephone}</td>
          </tr>
        </tbody>
      </table>
      <Link className="btn btn-primary" href={`/owners/${id}/add`}>Add Pet</Link>
      <h1>Pets and Visits</h1>
      <table className="table table-striped">
        <tbody>
          {pets.map((pet, index) => (
            <tr key={index}>
              <td>
                <dl className="dl-horizontal">
                  <dt>Name</dt>
                  <dd>{pet.name}</dd>

                  <dt>Birth Date</dt>
                  <dd>{pet.birthDate}</dd>

                  <dt>Type</dt>
                  <dd>{pet.type}</dd>
                </dl>
              </td>
              <td>
                <table className="table">
                  <thead>
                    <tr>
                      <th>Visit Date</th>
                      <th>Description</th>
                    </tr>
                  </thead>
                  <tbody>
                    {pet.visits.map((visit: any, index: number) => (
                      <tr key={index}>
                        <td>{visit.date}</td>
                        <td>{visit.description}</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
                <ul className="p-0">
                  <li><Link href="#">Edit Pet</Link></li>
                  <li><Link href={`/owners/${id}/pets/${pet.id}/add`}>Add Visit</Link></li>
                </ul>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}