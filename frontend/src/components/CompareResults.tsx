import React, {useEffect, useState} from 'react';
import {compareSetups} from '../services/api';
import {ComparisonItem} from "../types";

interface CompareResultsProps {
    setups: ComparisonItem[];
}

const CompareResults: React.FC<CompareResultsProps> = ({setups}) => {
    const [comparisonData, setComparisonData] = useState<any>(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchComparison = async () => {
            if (setups.length < 2) {
                setComparisonData(null);
                return;
            }

            setLoading(true);
            setError(null);

            try {
                const setupIds = setups.map(s => s.setup.id);
                const data = await compareSetups(setupIds);
                setComparisonData(data);
            } catch (err) {
                setError('Failed to fetch comparison data.');
                console.error(err);
            } finally {
                setLoading(false);
            }
        };

        fetchComparison();
    }, [setups]);

    if (loading) {
        return <div>Loading comparison...</div>;
    }

    if (error) {
        return <div>{error}</div>;
    }

    if (!comparisonData) {
        return null;
    }

    return (
        <div>
            <h3>Comparison Results</h3>
            <pre>{JSON.stringify(comparisonData, null, 2)}</pre>
        </div>
    );
};

export default CompareResults;
