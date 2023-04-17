import { useEffect, useState } from 'react';
import { useRouter } from 'next/router';

export default function VisitAdd() {
  const router = useRouter();
  const { id, pid } = router.query;

  const [visit, setVisit] = useState({
    date: '',
    description: ''
  });

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setVisit({...visit, [event.target.name]: event.target.value});
  };

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    const options = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        ...visit,
        petId: pid
      })
    };
    fetch(`http://localhost:8080/visits`, options)
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

  return (
    <>
      <h1>Add Visit</h1>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label htmlFor="name">Date</label>
          <input className="form-control" type="date" name="date" id="date" value={visit.date} onChange={handleChange}/>
        </div>

        <div className="mb-3">
          <label htmlFor="birthDate">Description</label>
          <input className="form-control" type="text" name="description" id="description" value={visit.description} onChange={handleChange}/>
        </div>

        <button type="submit" className="btn btn-primary">Add Visit</button>
      </form>
    </>
  );
}
