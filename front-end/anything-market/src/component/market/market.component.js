import { useState } from "react";

let Market = (props) => {
  const goods = [
    { id: 1, name: "Apple", price: 100 },
    { id: 2, name: "Banana", price: 100 },
  ];

  const [data, setData] = useState(goods);
  const handleChange = (id) => (e) => {
    let propName = e.target.name;
    if (propName == "price") {
      if (isNaN(e.target.value)) {
        alert("Invalid value!");
        return;
      }
    }
    setData(
      data.map((item) =>
        item.id === id ? { ...item, [propName]: e.target.value } : item
      )
    );
  };

  return (
    <div>
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Price</th>
          </tr>
        </thead>
        <tbody>
          {data.map((good, index) => (
            <tr key={good.id}>
              <td>
                <input
                  type="text"
                  name="name"
                  value={good.name}
                  onChange={handleChange(good.id)}
                />
              </td>
              <td>
                <input
                  type="text"
                  name="price"
                  value={good.price}
                  onChange={handleChange(good.id)}
                />
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Market;
