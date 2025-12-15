import { useEffect, useState } from "react";
import { obtenerProductos } from "../services/api";

export default function Productos() {
  const [productos, setProductos] = useState<any[]>([]);

  useEffect(() => {
    obtenerProductos().then(data => setProductos(data));
  }, []);

  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold mb-4">Productos</h1>

      <ul className="space-y-2">
        {productos.map((p) => (
          <li key={p.id} className="border p-3 rounded">
            <strong>{p.nombre}</strong> - ${p.precio}
          </li>
        ))}
      </ul>
    </div>
  );
}
