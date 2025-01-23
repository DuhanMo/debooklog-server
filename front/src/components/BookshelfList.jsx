import React, { useEffect, useState } from 'react';
import { fetchBookshelves } from '../api/bookshelfApi';

const BookshelfList = () => {
    const [bookshelves, setBookshelves] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const loadBookshelves = async () => {
            try {
                const data = await fetchBookshelves();
                setBookshelves(data); // 🔹 올바른 배열 데이터 설정
            } catch (err) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        };

        loadBookshelves();
    }, []);

    if (loading) return <p>Loading...</p>;
    if (error) return <p>Error: {error}</p>;

    return (
        <div>
            <h1>Bookshelves</h1>
            <ul>
                {bookshelves.length > 0 ? ( // 🔹 bookshelves가 비어있는 경우도 체크
                    bookshelves.map((shelf) => (
                        <li key={shelf.id}>
                            <img src={shelf.imageUrl} alt={shelf.name} width="50" />
                            {shelf.name}
                        </li>
                    ))
                ) : (
                    <p>No bookshelves found.</p>
                )}
            </ul>
        </div>
    );
};

export default BookshelfList;
