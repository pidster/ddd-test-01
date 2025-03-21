import React, { useState, useEffect } from 'react';
import { 
  Box, 
  Typography, 
  Chip,
  Paper,
  CircularProgress
} from '@mui/material';
import { DataGrid, GridColDef, GridRenderCellParams } from '@mui/x-data-grid';
import { useNavigate } from 'react-router-dom';
import { format } from 'date-fns';

import { ClaimDto, ClaimStatus } from '../../types/api';
import { getPaginated } from '../../services/api';
import { Card } from '../common/Card';
import { Button } from '../common/Button';

// Define status chip colors
const getStatusColor = (status: ClaimStatus) => {
  switch (status) {
    case ClaimStatus.SUBMITTED:
      return 'primary';
    case ClaimStatus.IN_REVIEW:
      return 'warning';
    case ClaimStatus.ACCEPTED:
      return 'success';
    case ClaimStatus.REJECTED:
      return 'error';
    case ClaimStatus.PAID:
      return 'info';
    default:
      return 'default';
  }
};

// Render status cell with colored chip
const StatusCell = (params: GridRenderCellParams<ClaimDto, ClaimStatus>) => {
  return (
    <Chip
      label={params.value?.toString().replace('_', ' ')}
      color={getStatusColor(params.value as ClaimStatus)}
      size="small"
    />
  );
};

// Format date values
const DateCell = (params: GridRenderCellParams<ClaimDto, string>) => {
  if (!params.value) return '-';
  return format(new Date(params.value), 'dd MMM yyyy');
};

// Format currency values
const CurrencyCell = (params: GridRenderCellParams<ClaimDto, number>) => {
  return new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD',
  }).format(params.value || 0);
};

const ClaimsList: React.FC = () => {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(true);
  const [claims, setClaims] = useState<ClaimDto[]>([]);
  const [page, setPage] = useState(0);
  const [pageSize, setPageSize] = useState(10);
  const [totalElements, setTotalElements] = useState(0);

  // Column definitions
  const columns: GridColDef[] = [
    { field: 'id', headerName: 'Claim ID', width: 120 },
    { field: 'policyNumber', headerName: 'Policy Number', width: 150 },
    { field: 'customerName', headerName: 'Customer', width: 180 },
    { field: 'description', headerName: 'Description', width: 220, flex: 1 },
    { 
      field: 'amount', 
      headerName: 'Amount', 
      width: 120,
      renderCell: CurrencyCell
    },
    { 
      field: 'submissionDate', 
      headerName: 'Submitted', 
      width: 130,
      renderCell: DateCell
    },
    { 
      field: 'status', 
      headerName: 'Status', 
      width: 130,
      renderCell: StatusCell
    },
    {
      field: 'actions',
      headerName: 'Actions',
      width: 120,
      sortable: false,
      renderCell: (params: GridRenderCellParams<ClaimDto>) => (
        <Button 
          variant="primary" 
          size="small" 
          onClick={() => navigate(`/claims/${params.row.id}`)}
        >
          Details
        </Button>
      ),
    },
  ];

  // Fetch claims data
  useEffect(() => {
    const fetchClaims = async () => {
      setLoading(true);
      try {
        const response = await getPaginated<ClaimDto>(
          '/claims', 
          page,
          pageSize,
          'submissionDate,desc'
        );
        setClaims(response.content);
        setTotalElements(response.metadata.totalElements);
      } catch (error) {
        console.error('Failed to fetch claims:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchClaims();
  }, [page, pageSize]);

  // Handle new claim button click
  const handleNewClaim = () => {
    navigate('/claims/new');
  };

  return (
    <Card
      title="Insurance Claims"
      action={
        <Button 
          variant="primary" 
          onClick={handleNewClaim}
        >
          New Claim
        </Button>
      }
    >
      {loading && claims.length === 0 ? (
        <Box sx={{ display: 'flex', justifyContent: 'center', p: 3 }}>
          <CircularProgress />
        </Box>
      ) : claims.length > 0 ? (
        <DataGrid
          rows={claims}
          columns={columns}
          pagination
          paginationMode="server"
          rowCount={totalElements}
          page={page}
          pageSize={pageSize}
          onPageChange={(newPage) => setPage(newPage)}
          onPageSizeChange={(newPageSize) => setPageSize(newPageSize)}
          pageSizeOptions={[5, 10, 25, 50]}
          disableRowSelectionOnClick
          autoHeight
          density="standard"
          sx={{ border: 'none' }}
        />
      ) : (
        <Box sx={{ p: 3, textAlign: 'center' }}>
          <Typography variant="body1" color="text.secondary">
            No claims found. Create a new claim to get started.
          </Typography>
          <Box sx={{ mt: 2 }}>
            <Button variant="primary" onClick={handleNewClaim}>
              Create New Claim
            </Button>
          </Box>
        </Box>
      )}
    </Card>
  );
};

export default ClaimsList; 