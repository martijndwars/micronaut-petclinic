import { useEffect, useState } from 'react';
import { useRouter } from 'next/router';

export default function PetAdd() {
  const router = useRouter();
  const { id } = router.query;

  const [owner, setOwner] = useState<any>({});

  const fetchOwner = () => {
    fetch(`http://localhost:8080/owners/${id}`)
      .then((response) => response.json())
      .then(setOwner)
      .catch((err) => {
        console.log(err.message);
      });
  }

  const [pet, setPet] = useState({
    name: '',
    birthDate: '',
    type: '',
  });

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPet({...pet, [event.target.name]: event.target.value});
  };

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    const options = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        ...pet,
        ownerId: id
      })
    };
    fetch(`http://localhost:8080/pets`, options)
      .then((response) => {
        if (response.status != 201) {
          alert('Error');
        } else {
          router.push(`/owners/${id}`);
        }
      })
      .catch((err) => {
        console.log(err.message);
      });
    event.preventDefault();
  };

  useEffect(() => {
    if (id == undefined) {
      return;
    }
    fetchOwner();
  }, [id]);

  return (
    <>
      <h1>Add Pet</h1>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label>Owner</label>
          <input className="form-control" type="text" name="owner" id="owner" disabled={true} value={`${owner.firstName} ${owner.lastName}`}/>
        </div>

        <div className="mb-3">
          <label htmlFor="name">Name</label>
          <input className="form-control" type="text" name="name" id="name" value={pet.name} onChange={handleChange}/>
        </div>

        <div className="mb-3">
          <label htmlFor="birthDate">Birth Date</label>
          <input className="form-control" type="date" name="birthDate" id="birthDate" value={pet.birthDate} onChange={handleChange}/>
        </div>

        <div className="mb-3">
          <label htmlFor="city">Type</label>
          <input className="form-control" type="text" name="type" id="type" value={pet.type} onChange={handleChange}/>
        </div>

        <button type="submit" className="btn btn-primary">Add Pet</button>
      </form>
    </>
  );
}
