import React from 'react';
import { Card as MuiCard, CardContent, CardHeader, CardActions, CardProps as MuiCardProps, styled } from '@mui/material';

export interface CustomCardProps extends MuiCardProps {
  title?: React.ReactNode;
  subheader?: React.ReactNode;
  action?: React.ReactNode;
  footer?: React.ReactNode;
  noPadding?: boolean;
  variant?: 'outlined' | 'elevation';
  elevation?: number;
}

const StyledCard = styled(MuiCard)(({ theme }) => ({
  borderRadius: '8px',
  overflow: 'hidden',
}));

export const Card: React.FC<CustomCardProps> = ({
  title,
  subheader,
  action,
  footer,
  children,
  noPadding = false,
  variant = 'elevation',
  elevation = 1,
  ...restProps
}) => {
  return (
    <StyledCard variant={variant} elevation={elevation} {...restProps}>
      {(title || subheader || action) && (
        <CardHeader
          title={title}
          subheader={subheader}
          action={action}
        />
      )}
      <CardContent sx={{ padding: noPadding ? 0 : undefined }}>
        {children}
      </CardContent>
      {footer && <CardActions>{footer}</CardActions>}
    </StyledCard>
  );
};

export default Card; 