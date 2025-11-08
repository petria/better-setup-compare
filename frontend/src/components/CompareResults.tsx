import React, {useEffect, useState} from 'react';
import {compareSetups} from '../services/api';
import {ComparisonItem} from "../types";
import {Table} from "react-bootstrap";

interface CompareResultsProps {
    setups: ComparisonItem[];
}

const getDifferenceColor = (diffValue: string | null) => {
    if (diffValue === null || diffValue === "0") {
        return '';
    }
    const diff = parseInt(diffValue, 10);
    if (isNaN(diff)) {
        return '';
    }
    return diff < 0 ? 'table-primary' : 'table-danger'; // Using Bootstrap's contextual classes
};

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

    if (!comparisonData || !comparisonData.carSetupDifferences) {
        return null;
    }

    const {carSetupDifferences} = comparisonData;
    const setupIds = carSetupDifferences.map((diff: any) => diff.setupId);

    // Create a map of all possible config keys for all groups
    const allConfigKeys = new Map<string, Set<string>>();
    carSetupDifferences.forEach((diff: any) => {
        diff.setupDifferenceGroups.forEach((group: any) => {
            if (!allConfigKeys.has(group.setupGroupName)) {
                allConfigKeys.set(group.setupGroupName, new Set<string>());
            }
            const groupKeys = allConfigKeys.get(group.setupGroupName);
            group.setupDifferences.forEach((d: any) => {
                groupKeys!.add(d.configKey);
            });
        });
    });


    return (
        <div>
            <h3>Comparison Results</h3>
            <Table striped bordered hover responsive>
                <thead>
                <tr>
                    <th>Setup Group</th>
                    <th>Configuration</th>
                    {setupIds.map((id: number, index: number) => (
                        <th key={id}>{id} {index > 0 && '(diff)'}</th>
                    ))}
                </tr>
                </thead>
                <tbody>
                {Array.from(allConfigKeys.keys()).map(groupName => (
                    <React.Fragment key={groupName}>
                        <tr>
                            <td colSpan={setupIds.length + 2}><strong>{groupName}</strong></td>
                        </tr>
                        {Array.from(allConfigKeys.get(groupName)!).map(configKey => {
                            return (
                                <tr key={configKey}>
                                    <td></td>
                                    <td>{configKey}</td>
                                    {carSetupDifferences.map((diff: any, index: number) => {
                                        const group = diff.setupDifferenceGroups.find((g: any) => g.setupGroupName === groupName);
                                        const difference = group?.setupDifferences.find((d: any) => d.configKey === configKey);
                                        const value = difference ? difference.configValue.split('=')[1] : '-';
                                        const diffValue = difference ? difference.configDifference : null;

                                        return (
                                            <td key={diff.setupId} className={index > 0 ? getDifferenceColor(diffValue) : ''}>
                                                {value} {index > 0 && diffValue !== null && `(${diffValue})`}
                                            </td>
                                        );
                                    })}
                                </tr>
                            );
                        })}
                    </React.Fragment>
                ))}
                </tbody>
            </Table>
        </div>
    );
};

export default CompareResults;
