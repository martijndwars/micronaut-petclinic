import { useEffect, useState } from 'react';
import { useRouter } from 'next/router';

export default function OwnerAdd() {
  const router = useRouter()
  const [owner, setOwner] = useState({
    firstName: '',
    lastName: '',
    address: '',
    city: '',
    telephone: ''
  });

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setOwner({...owner, [event.target.name]: event.target.value});
  };

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    const options = {
     method: 'POST',
     headers: {
       'Content-Type': 'application/json'
     },
     body: JSON.stringify(owner)
    };
    fetch(`http://localhost:8080/owners`, options)
      .then((response) => {
        router.push('/owners');
      })
      .catch((err) => {
        console.log(err.message);
      });
    event.preventDefault();
  };

  return (
    <>
      <h1>New Owner</h1>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label htmlFor="firstName">First Name</label>
          <input className="form-control" type="text" name="firstName" id="firstName" value={owner.firstName} onChange={handleChange}/>
        </div>

        <div className="mb-3">
          <label htmlFor="lastName">Last Name</label>
          <input className="form-control" type="text" name="lastName" id="lastName" value={owner.lastName} onChange={handleChange}/>
        </div>

        <div className="mb-3">
          <label htmlFor="address">Address</label>
          <input className="form-control" type="text" name="address" id="address" value={owner.address} onChange={handleChange}/>
        </div>

        <div className="mb-3">
          <label htmlFor="city">City</label>
          <input className="form-control" type="text" name="city" id="city" value={owner.city} onChange={handleChange}/>
        </div>

        <div className="mb-3">
          <label htmlFor="telephone">Telephone</label>
          <input className="form-control" type="text" name="telephone" id="telephone" value={owner.telephone} onChange={handleChange}/>
        </div>

        <button type="submit" className="btn btn-primary">Add Owner</button>
      </form>
    </>
  );
}
